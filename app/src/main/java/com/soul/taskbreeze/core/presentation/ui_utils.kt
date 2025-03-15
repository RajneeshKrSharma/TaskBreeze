package com.soul.taskbreeze.core.presentation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.sin

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

@Composable
fun LoadingUi() {
    val infiniteTransition = rememberInfiniteTransition()

    // Expanding & Contracting Circle Animation
    val circleSize by infiniteTransition.animateFloat(
        initialValue = 60f,
        targetValue = 90f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    // Glow effect
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    // Wave background animation
    val waveOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 100f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFF1A1A2E), Color(0xFF16213E))))
            .drawBehind {
                for (i in 0..5) {
                    drawCircle(
                        color = Color(0xFF0F3460).copy(alpha = 0.1f),
                        radius = 400f - (i * 80) + waveOffset,
                        center = center
                    )
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(circleSize.dp)
                    .background(
                        Brush.radialGradient(listOf(Color(0xFF00D4FF), Color(0xFF0094FF))),
                        shape = CircleShape
                    )
                    .shadow(16.dp, shape = CircleShape)
                    .graphicsLayer {
                        alpha = glowAlpha
                    }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Loading...",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.graphicsLayer { translationY = sin(waveOffset / 50).toFloat() * 10 }
            )
        }
    }
}


