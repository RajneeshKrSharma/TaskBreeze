package com.unique.tba.core.presentation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
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

    // Wave motion animation
    val waveOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 100f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    // Text fade-in effect
    val textAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    // Full-screen Box
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background with blur effect
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f)) // Semi-transparent overlay
        )

        // Foreground layer for animations and text
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .drawBehind {
                        for (i in 0..3) {
                            drawCircle(
                                color = Color(0xFF3F51B5).copy(alpha = 0.1f),
                                radius = 400f - (i * 80) + waveOffset,
                                center = center
                            )
                        }
                    }
            )

            // Text on top of blurred background
            Text(
                text = "Please wait...",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White.copy(alpha = textAlpha),
                modifier = Modifier.graphicsLayer {
                    translationY = sin(waveOffset / 50) * 10
                }
            )
        }
    }
}

@Composable
fun GradientButton(
    text: String,
    btnGradient: Brush? = null,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    icon: Int? = null,
    onClick: () -> Unit
) {
    // Define a gradient brush for enabled state
    val gradient = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFF18CE00), // start color
            Color(0xFF0A5912)  // end color
        )
    )

    // Define a solid gray background for disabled state
    val disabledColor = Color(0xFFB0B0B0)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                shape = RoundedCornerShape(12.dp),
                brush = if (enabled) btnGradient ?: gradient else SolidColor(disabledColor) // Handle enabled state
            )
            .clip(RoundedCornerShape(12.dp))
            .clickable(enabled = enabled) { onClick() },
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 24.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                icon?.let {
                    Image(
                        modifier = iconModifier,
                        painter = painterResource(icon),
                        contentDescription = null
                    )
                    Spacer(Modifier.width(8.dp))
                }
                Text(
                    text = text,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun SquareBoundaryGradient(
    modifier: Modifier = Modifier,
    progressTime: Int
) {
    val gradientColors = listOf(
        Color(0xFFFF1200),
        Color(0xFFE3004D), // Pink gradient color
    )

    val progress = remember { Animatable(0.1f) } // Start from 0.1 instead of 0 to avoid invisibility

    LaunchedEffect(Unit) {
        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = progressTime, easing = LinearEasing)
        )
    }

    Canvas(modifier = modifier) {
        val size = size.width
        val strokeWidth = 12f

        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(size, 0f)
            lineTo(size, size)
            lineTo(0f, size)
            close()
        }

        val pathLength = size * 4
        val dashLength = pathLength * progress.value

        // Remove pathEffect to see if gradient is the issue
        val pathEffect = PathEffect.dashPathEffect(
            floatArrayOf(dashLength, pathLength - dashLength),
            0f
        )

        drawPath(
            path = path,
            brush = Brush.sweepGradient(gradientColors), // Sweep gradient for better color blending
            style = Stroke(
                width = strokeWidth,
                cap = StrokeCap.Butt,
                pathEffect = pathEffect // Apply path effect carefully
            )
        )
    }
}
