package com.soul.taskbreeze.auth.login.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.soul.taskbreeze.auth.login.data.remote.dto.LoginViaOtpRequestDto
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    viewModel: LoginViewmodel = hiltViewModel(),
    navController: NavController
) {
    val otpState = remember { mutableStateListOf("", "", "", "", "", "") }
    val emailIdState = remember { mutableStateOf("") }
    var isEmailValid = remember { mutableStateOf(false) }
    val requestOtpState = viewModel.getOtpState.value
    val requestLoginViaOtpState = viewModel.loginViaOtpState.value

    val focusRequesters = List(6) { FocusRequester() }
    val focusManager = LocalFocusManager.current

    var timerValue = remember { mutableIntStateOf(120) }
    val timerRunning = remember { mutableStateOf(false) }

    LaunchedEffect(requestOtpState.data) {
        if (requestOtpState.data != null) {
            focusRequesters[0].requestFocus()
            timerValue.value = 120 // Reset timer to 2 minutes
            timerRunning.value = true // Start timer
        }
    }

    LaunchedEffect(timerRunning.value) {
        if (timerRunning.value) {
            while (timerValue.intValue > 0) {
                delay(1000L)
                timerValue.intValue --
            }
            timerRunning.value = false
        }
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
        Text(
            text = "Enter your email id",
            fontSize = 18.sp,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.Start),
        )
        OutlinedTextField(
            value = emailIdState.value,
            onValueChange = { newValue ->
                emailIdState.value = newValue
                isEmailValid.value = newValue.matches(emailPattern)
            },
            label = { Text("Email Id") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
            enabled = requestOtpState.data == null,
            supportingText = {
                if (!isEmailValid.value && emailIdState.value.isNotEmpty()) {
                    Text("Invalid email format", color = Color.Red, fontSize = 12.sp)
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        requestOtpState.data?.let {
            Text(
                text = String.format("%02d:%02d", timerValue.intValue / 60, timerValue.intValue % 60),
                fontSize = 16.sp,
                color = Color.Red,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(6) { index ->
                    OutlinedTextField(
                        value = otpState[index],
                        onValueChange = { newValue ->
                            if (newValue.length <= 1) {
                                otpState[index] = newValue
                                if (newValue.isNotEmpty() && index < 5) {
                                    focusRequesters[index + 1].requestFocus()
                                } else if (index == 5) {
                                    focusManager.clearFocus()
                                }
                            }
                        },
                        modifier = Modifier
                            .width(50.dp)
                            .height(80.dp)
                            .focusRequester(focusRequesters[index]),
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            LoginButton(
                btnName = "Login via OTP",
                isEnabled = otpState.all { it.isNotEmpty() }
            ) {
                if (otpState.size == 6) {
                    viewModel.loginViaOtp(
                        LoginViaOtpRequestDto(
                            emailId = emailIdState.value,
                            otp = requestOtpState.data.otp.toString(),
                            fcmToken = "RANDOM"
                        )
                    )
                }
            }
        } ?: LoginButton(
            btnName = "Request OTP",
            isEnabled = isEmailValid.value
        ) {
            viewModel.getOtp(emailIdState.value)
        }
    }
}


@Composable
fun LoginButton(
    btnName: String,
    isEnabled: Boolean,
    onButtonPressed: () -> Unit
) {
    Button(
        onClick = onButtonPressed,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B)),
        enabled = isEnabled, // Ensuring the button respects the isEnabled state
        shape = RoundedCornerShape(8.dp) // Adding rounded corners
    ) {
        Text(
            text = btnName,
            fontSize = 16.sp,
            color = Color.White
        )
    }
}


val emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$".toRegex()

