package com.subhamkumar.boxboxapp.ui.home.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.subhamkumar.boxboxapp.R
import com.subhamkumar.boxboxapp.ui.theme.SpaceGrotesk


@Composable
fun StatCard(
    modifier: Modifier = Modifier,
    label: String = "7015.3",
    unit: String = "km",
    @DrawableRes imageRes: Int = R.drawable.ic_distance_icon,
    progress: Float = 0.40f,
    animateProgress: Boolean = true,
    fontFamily: FontFamily = SpaceGrotesk
) {
    val clamped = progress.coerceIn(0f, 1f)
    val target = if (animateProgress) clamped else clamped
    val animatedProgress by animateFloatAsState(
        targetValue = target, animationSpec = tween(
            durationMillis = 500, easing = FastOutSlowInEasing
        )
    )

    val cardRadius = 16.dp
    val redColor = Color(0xFFF51A1E)
    val blackPanel = Color(0xFF0B0B0B)
    val outline = Color(0xFF212121)

    Card(
        modifier = modifier
            .height(64.dp)
            .clip(RoundedCornerShape(cardRadius)),
        shape = RoundedCornerShape(cardRadius),
        border = BorderStroke(1.dp, Color(0xFF212121)),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(blackPanel)
                .padding(1.dp)
        ) {
            Box(modifier = Modifier.matchParentSize()) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(animatedProgress)
                        .clip(RoundedCornerShape(topStart = cardRadius, bottomStart = cardRadius))
                        .background(
                            Brush.horizontalGradient(listOf(redColor, redColor.copy(alpha = 0.95f)))
                        )
                )

                if (animatedProgress in 0.001f..0.999f) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(animatedProgress)
                    ) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .width(1.dp)
                                .fillMaxHeight()
                                .background(outline.copy(alpha = 0.6f))
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 9.dp, end = 9.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = "stat icon",
                        modifier = Modifier.size(28.dp),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = label, style = TextStyle(
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp,
                                lineHeight = 28.sp,
                            ), color = Color.White, maxLines = 1
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Box(
                            modifier = Modifier
                                .height(28.dp)
                                .defaultMinSize(minWidth = 36.dp)
                                .padding(top = 8.dp)
                                .clip(RoundedCornerShape(6.dp)),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = unit, style = TextStyle(
                                    fontFamily = fontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp,
                                    lineHeight = 28.sp,
                                    letterSpacing = 0.sp
                                ), color = Color.White, maxLines = 1, textAlign = TextAlign.Start
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .padding(0.5.dp)
                    .clip(RoundedCornerShape(cardRadius))
            ) {}
        }
    }
}