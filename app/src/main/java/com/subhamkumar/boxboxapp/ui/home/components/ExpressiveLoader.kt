package com.subhamkumar.boxboxapp.ui.home.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp

@Composable
fun ExpressiveLoader(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "loader")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 360f, animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing)
        ), label = "rotation"
    )

    Box(
        modifier = modifier, contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .rotate(angle), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                strokeWidth = 6.dp,
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )
        }
    }

}