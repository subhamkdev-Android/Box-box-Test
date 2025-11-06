package com.subhamkumar.boxboxapp.ui.home.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PagerIndicator(
    pagerState: PagerState,
    pageCount: Int,
    modifier: Modifier = Modifier,
    activeColor: Color = Color.White,
    inactiveColor: Color = Color.White.copy(alpha = 0.35f),
    activeWidth: Dp = 28.dp,
    inactiveWidth: Dp = 6.dp,
    height: Dp = 6.dp,
    gap: Dp = 8.dp
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(gap),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val current = pagerState.currentPage
        for (i in 0 until pageCount) {
            val isActive = i == current
            val targetWidth = if (isActive) activeWidth else inactiveWidth
            val animatedWidth by animateDpAsState(targetValue = targetWidth)
            val animatedColor by animateColorAsState(targetValue = if (isActive) activeColor else inactiveColor)

            Box(
                modifier = Modifier
                    .size(width = animatedWidth, height = height)
                    .clip(RoundedCornerShape(percent = 50))
                    .background(animatedColor)
            )
        }
    }
}