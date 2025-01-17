package com.soul.taskbreeze.auth.login.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.soul.taskbreeze.R
import com.soul.taskbreeze.auth.login.data.remote.dto.LoginViaOtpRequestDto
import com.soul.taskbreeze.pre_auth.pre_auth_loading.presentation.Screen

@Composable
fun LoginScreen(
    viewModel: LoginViewmodel = hiltViewModel(),
    navController: NavController
) {
    // State to hold OTP values (4 digits)
    val otpState = remember { mutableStateListOf("", "", "", "") }

    // State to hold the mobile number entered by the user
    val mobileNumberState = remember { mutableStateOf("") }

    val requestOtpState = viewModel.getOtpState.value
    val requestLoginViaOtpState = viewModel.loginViaOtpState.value

    LaunchedEffect(requestLoginViaOtpState) {
        if (requestLoginViaOtpState.data != null) {
            navController.navigate(Screen.HomeScreen.route)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header Text
        Text(
            text = "Enter your mobile number",
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 16.dp),
        )

        // Row with Indian flag and +91 country code
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.indian_flag),
                contentDescription = "Indian Flag",
                modifier = Modifier
                    .size(40.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "+91",
                fontSize = 18.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            OutlinedTextField(
                value = mobileNumberState.value, // Bind this to the state variable
                onValueChange = { newValue ->
                    // Only update the value if it is a valid phone number (numeric only)
                    if (newValue.all { it.isDigit() } && newValue.length <= 10) {
                        mobileNumberState.value = newValue
                    }
                },
                label = { Text("Enter mobile number") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // OTP Entry Section
        requestOtpState.data?.let { data ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Convert otp string to char array and iterate over it
                data.otp?.toString()?.forEachIndexed { index, otpChar ->
                    OutlinedTextField(
                        value = otpChar.toString(), // Binding to the specific OTP field
                        onValueChange = { newValue ->
                            // Only update the current OTP box if the input is a number
                            if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                                otpState[index] = newValue
                            }
                        },
                        modifier = Modifier
                            .width(50.dp)
                            .height(60.dp),
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true
                    )
                }
                    ?: LoginButton(
                        btnName = "Request OTP"
                    ) {
                        viewModel.getOtp("+91${mobileNumberState.value}")
                    }
            }
            Spacer(modifier = Modifier.height(24.dp))

            // Login via otp
            LoginButton(
                btnName = "Login via OTP"
            ) {
                viewModel.loginViaOtp(
                    LoginViaOtpRequestDto(
                        mobileNo = "+91${mobileNumberState.value}",
                        otp = data.otp.toString(),
                        fcmToken = "RANDOM"
                    )
                )
            }

            Spacer(modifier = Modifier.height(24.dp))


        } ?: LoginButton(
            btnName = "Request OTP"
        ) {
            viewModel.getOtp("+91${mobileNumberState.value}")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Login via Google Button
        OutlinedButton(
            onClick = {

            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            ) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Login via Google",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        }
    }
}


@Composable
fun LoginButton(
    btnName: String,
    onButtonPressed: () -> Unit) {
    Button(
        onClick = onButtonPressed,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B))
    ) {
        Text(
            text = btnName,
            fontSize = 16.sp,
            color = Color.White
        )
    }
}



