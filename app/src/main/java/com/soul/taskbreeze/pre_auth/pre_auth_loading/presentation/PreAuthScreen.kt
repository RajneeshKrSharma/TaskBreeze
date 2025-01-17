package com.soul.taskbreeze.pre_auth.pre_auth_loading.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.soul.taskbreeze.core.presentation.BaseCompose
import com.soul.taskbreeze.core.presentation.GradientProgressBar

@Composable
fun PreAuthScreen(
    viewModel: PreAuthViewmodel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.value
    BaseCompose(
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(paddingValues)
            ) {
                // Header Section
                Text(
                    text = "Pre-Authorization",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 40.dp) // Padding for the header
                )

                // Linear Progress Indicator with better styling and padding
                state.data?.let { data ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp) // General padding to prevent UI from touching the screen edges
                    ) {
                        Column(
                            modifier = Modifier.align(Alignment.Center),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            GradientProgressBar(data)
                            Text("Building Progress: ${"%.0f".format(data * 100)}%")

                            // Check if progress is complete
                            if (data >= 1.0f) {
                                // Trigger navigation
                                LaunchedEffect(Unit) {
                                    navController.navigate(Screen.LoginScreen.route)
                                }
                            }
                        }
                    }
                }

                // Error message styling with improved spacing and color
                if (state.error.isNotBlank()) {
                    Text(
                        text = state.error,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)
                            .padding(top = 8.dp) // Padding between error message and loading indicator
                    )
                }

                // Circular Progress Indicator with customized color and padding
                if (state.isLoading) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(50.dp) // Larger loading indicator for better visibility
                    )
                }
            }
        }
    )
}
