package com.soul.taskbreeze.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GradientProgressBar(progress: Float) {
    val gradient = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFF00C853), // Start color: Green
            Color(0xFF006400)  // End color: Dark Green
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(6.dp)
            .clip(MaterialTheme.shapes.small)
            .background(Color.LightGray) // Background track color
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress) // Width proportional to the progress
                .fillMaxHeight()
                .background(brush = gradient) // Apply gradient
        )
    }
}
