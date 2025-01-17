package com.soul.taskbreeze.core.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseCompose(
    topBar: @Composable (() -> Unit)? = {
        TopAppBar(
            title = { Text(text = "TaskBreeze") },
            modifier = Modifier.padding(16.dp),
        )
    },
    bottomBar: @Composable (() -> Unit)? = null,
    content: @Composable (paddingValues: PaddingValues) -> Unit,
) {
    // Observe the connectivity state using collectAsState
    val viewModel: BaseViewModel = hiltViewModel()
    val isConnected by viewModel.isConnected.collectAsState()
    val showStrip by viewModel.showStrip.collectAsState()

    Scaffold(
        topBar = {
            topBar?.invoke()
        },
        bottomBar = { bottomBar?.invoke() },
        content = { paddingValues ->
            Box(modifier = Modifier.fillMaxSize()) {
                content(paddingValues)

                // Animated Connectivity Strip with slide animations
                AnimatedVisibility(
                    visible = showStrip,
                    enter = slideInVertically(
                        initialOffsetY = { it },
                        animationSpec = tween(durationMillis = 500)
                    ) + fadeIn(animationSpec = tween(durationMillis = 500)),
                    exit = slideOutVertically(
                        targetOffsetY = { it },
                        animationSpec = tween(durationMillis = 500)
                    ) + fadeOut(animationSpec = tween(durationMillis = 500)),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = paddingValues.calculateBottomPadding())
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(if (isConnected) Color(0xFF0AB757) else Color(0xFFDE2A2A))
                            .padding(2.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (isConnected) "Back Online" else "No Internet Connection",
                            color = Color.White,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    )
}




