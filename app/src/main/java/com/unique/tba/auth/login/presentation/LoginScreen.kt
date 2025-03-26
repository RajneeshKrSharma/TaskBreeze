package com.unique.tba.auth.login.presentation

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.auth.api.identity.Identity
import com.unique.tba.R
import com.unique.tba.auth.login.data.remote.dto.LoginViaOtpRequestDto
import com.unique.tba.core.presentation.GradientButton
import com.unique.tba.core.presentation.LoadingUi
import com.unique.tba.core.presentation.SquareBoundaryGradient
import com.unique.tba.core.util.OtpExpiryInfo
import com.unique.tba.core.util.Resource
import com.unique.tba.pre_auth.presentation.Screen
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    viewModel: LoginViewmodel = hiltViewModel(),
    navController: NavController,
    context: Context
) {

    val emailIdState = remember { mutableStateOf("") }

    val authorizationLauncher = rememberAuthorizationLauncher(context = context)

    LaunchedEffect(viewModel.getAuthorizationRequest.value) {
        viewModel.getAuthorizationRequest.value?.let { request ->
            Identity.getAuthorizationClient(context)
                .authorize(request)
                .addOnSuccessListener { authorizationResult ->
                    if (authorizationResult.hasResolution()) {
                        authorizationResult.pendingIntent?.intentSender?.let { intentSender ->
                            authorizationLauncher.launch(IntentSenderRequest.Builder(intentSender).build())
                        }
                    } else {
                        Log.d("Rajneesh", "LoginScreen: authorizationResult.serverAuthCode : ${authorizationResult.serverAuthCode} ")
                        authorizationResult.serverAuthCode?.let { code ->
                            viewModel.getOAuthAccessToken(code= code)
                        } ?:  Toast.makeText(context, "Unable to fetch authorization details", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(context, e.stackTrace.toString(), Toast.LENGTH_SHORT).show()
                }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.weight(0.6f)
            ) {
                CreateLoginUI(
                    emailIdState = emailIdState,
                    viewModel = viewModel,
                    navController = navController
                )
            }

            Box(
                modifier = Modifier
                    .weight(0.4f),
                contentAlignment = Alignment.Center
            ) {
                GradientButton(
                    text = "Login via Google",
                    btnGradient = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF000000), // start color
                            Color(0xFF101010)  // end color
                        )
                    ),
                    icon = R.drawable.google_icon,
                    iconModifier = Modifier.size(20.dp)
                ) {
                    viewModel.onSignInWithGoogle(context = context)
                }
            }
        }

        with(viewModel) {
            if (getOtpState.value is Resource.Loading
                || loginViaOtpState.value is Resource.Loading
                || oAuthAccessTokenState.value is Resource.Loading
                || convertAccessTokenState.value is Resource.Loading
            ) {
                LoadingUi()
            }
        }
    }
}


@Composable
fun LoginButton(
    btnName: String,
    isEnabled: Boolean = false,
    onButtonPressed: () -> Unit) {
    GradientButton(
        text = btnName,
        enabled = isEnabled,
        modifier = Modifier.fillMaxWidth(),
        onClick = onButtonPressed
    )
}

val emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$".toRegex()

fun String.isEmailValid(): Boolean = this.run { isNotEmpty() && matches(emailPattern) }

@Composable
fun ObserveLoginErrors(viewModel: LoginViewmodel) {
    val context = LocalContext.current

    // Track only the latest error message
    val errorMessage by remember {
        derivedStateOf {
            when {
                viewModel.getOtpState.value is Resource.Error -> viewModel.getOtpState.value.message
                viewModel.loginViaOtpState.value is Resource.Error -> viewModel.loginViaOtpState.value.message
                else -> null
            }
        }
    }

    val latestErrorMessage = rememberUpdatedState(errorMessage)

    LaunchedEffect(errorMessage) {
        latestErrorMessage.value?.let { msg ->
            if (msg.isNotEmpty()) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun CreateLoginUI(
    emailIdState: MutableState<String>,
    viewModel: LoginViewmodel,
    navController: NavController
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val timerState = remember { mutableIntStateOf(OtpExpiryInfo.OTP_EXPIRY_TIME.time) }
    val isTimerRunning = remember { mutableStateOf(false) }

    val context = LocalContext.current
    fun onTimerFinished() {
        Toast.makeText(context, "OTP Expired", Toast.LENGTH_SHORT).show()
        navController.navigate(Screen.LoginScreen.route) {
            popUpTo(Screen.LoginScreen.route) { inclusive = true }
        }
    }

    LaunchedEffect(viewModel.getOtpState.value) {
        if (viewModel.getOtpState.value is Resource.Success) {
            timerState.intValue = OtpExpiryInfo.OTP_EXPIRY_TIME.time / 1000
            isTimerRunning.value = true
            while (timerState.intValue > 0) {
                delay(1000L)
                timerState.intValue--
            }

            isTimerRunning.value = false
            onTimerFinished()
        }
    }

    ObserveLoginErrors(viewModel)

    if (viewModel.loginViaOtpState.value is Resource.Success
        || viewModel.convertAccessTokenState.value is Resource.Success) {
        navController.navigate(Screen.HomeScreen.route)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Let's Login . .",
            fontSize = 40.sp,
            fontWeight = FontWeight.W900,
            modifier = Modifier.align(Alignment.Start),
        )

        Spacer(modifier = Modifier.height(24.dp))

        // ✅ Email Input Field
        OutlinedTextField(
            value = emailIdState.value,
            onValueChange = { newValue ->
                emailIdState.value = newValue
            },
            label = { Text("Email Id") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth(),
            enabled = viewModel.getOtpState.value !is Resource.Success,
            supportingText = {
                if (emailIdState.value.isNotEmpty() && emailIdState.value.isEmailValid().not()) {
                    Text("Invalid email format", color = Color.Red, fontSize = 12.sp)
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // ✅ Show OTP Input Section When OTP is Received
        if (viewModel.getOtpState.value is Resource.Success) {
            val otpFieldState = remember { mutableStateListOf("", "", "", "") }
            val focusManager = LocalFocusManager.current

            Text(
                modifier = Modifier.padding(bottom = 16.dp, top = 8.dp),
                text = "OTP will expired in : ${timerState.intValue} sec",
                fontSize = 16.sp, color = Color.Red
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(4) { index ->
                    Box(
                        modifier = Modifier
                            .width(60.dp)
                            .height(60.dp)
                    ) {
                        OutlinedTextField(
                            value = otpFieldState[index],
                            onValueChange = { newValue ->
                                if (newValue.length <= 1) {
                                    otpFieldState[index] = newValue
                                    if (newValue.isNotEmpty() && index < 3) {
                                        focusManager.moveFocus(FocusDirection.Right)
                                    }
                                }
                            },
                            modifier = Modifier
                                .width(60.dp)
                                .height(60.dp),
                            textStyle = LocalTextStyle.current.copy(
                                textAlign = TextAlign.Center,
                                fontSize = 18.sp
                            ),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            singleLine = true
                        )
                        SquareBoundaryGradient(
                            modifier = Modifier
                                .width(60.dp)
                                .height(60.dp),
                            OtpExpiryInfo.OTP_EXPIRY_TIME.time
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ✅ Login Button
            LoginButton(
                btnName = "Login Via OTP",
                isEnabled = otpFieldState.all { it.isNotEmpty() }
            ) {
                if (otpFieldState.all { it.isNotEmpty() }) {
                    keyboardController?.hide()
                    viewModel.loginViaOtp(
                        LoginViaOtpRequestDto(
                            emailId = emailIdState.value,
                            otp = otpFieldState.joinToString(""),
                            fcmToken = "RANDOM"
                        )
                    )
                }
            }
        } else {
            // ✅ Request OTP Button
            LoginButton(
                btnName = "Request OTP",
                isEnabled = emailIdState.value.isEmailValid()
            ) {
                keyboardController?.hide()
                viewModel.getOtp(emailId = emailIdState.value)
            }
        }
    }
}

@Composable
private fun rememberAuthorizationLauncher(
    context: Context
) = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.StartIntentSenderForResult()
) { activityResult ->
    if (activityResult.resultCode == Activity.RESULT_OK) {
        Identity.getAuthorizationClient(context).getAuthorizationResultFromIntent(activityResult.data)
    }
}

