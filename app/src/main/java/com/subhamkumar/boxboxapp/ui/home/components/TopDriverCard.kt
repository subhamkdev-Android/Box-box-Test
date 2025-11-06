package com.subhamkumar.boxboxapp.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.subhamkumar.boxboxapp.R
import com.subhamkumar.boxboxapp.data.model.Driver
import com.subhamkumar.boxboxapp.ui.theme.SpaceGrotesk

@Composable
fun TopDriverCard(
    driver: Driver, navController: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .clip(RoundedCornerShape(0.16.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFF6F00), Color(0xFFFF6F00), Color(0xFF000000)
                    )
                )
            )
    ) {
        Text(
            text = driver.firstName.uppercase(),
            style = TextStyle(
                fontFamily = SpaceGrotesk,
                fontWeight = FontWeight.Bold,
                fontSize = 164.sp,
                lineHeight = 164.sp,
                letterSpacing = (-12).sp
            ),
            color = Color.White.copy(alpha = 0.3f),
            modifier = Modifier
                .width(442.dp)
                .height(164.dp)
                .absoluteOffset(x = 20.dp, y = 70.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.lando_norris),
            contentDescription = driver.fullName,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .width(300.dp)
                .fillMaxHeight()
                .offset(x = 36.dp, y = 40.dp)
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent, Color(0xE6000000)
                        ), startY = 400f, endY = Float.POSITIVE_INFINITY
                    )
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 20.dp, bottom = 22.dp)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_pos),
                    contentDescription = "position icon",
                    modifier = Modifier.size(14.dp)
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = "${driver.position} Pos", color = Color(0xFFFFFFFF), style = TextStyle(
                        fontFamily = SpaceGrotesk,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        lineHeight = 16.sp,
                        letterSpacing = 0.sp
                    ), modifier = Modifier, maxLines = 1
                )


                Spacer(modifier = Modifier.width(12.dp))

                Image(
                    painter = painterResource(id = R.drawable.ic_wins),
                    contentDescription = "wins icon",
                    modifier = Modifier.size(14.dp)
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = "${driver.wins} Wins", color = Color(0xFFFFFFFF), style = TextStyle(
                        fontFamily = SpaceGrotesk,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        lineHeight = 16.sp,
                        letterSpacing = 0.sp
                    ), modifier = Modifier, maxLines = 1
                )
            }

            Spacer(modifier = Modifier.height(10.dp))


            Row(verticalAlignment = Alignment.Bottom) {

                Text(
                    text = driver.points.toString(),
                    style = TextStyle(
                        fontFamily = SpaceGrotesk,
                        fontWeight = FontWeight.W300,
                        fontSize = 72.sp,
                        lineHeight = 70.sp,
                        letterSpacing = 0.sp,
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFFFFFFFF), Color(0xFFFF5A08)
                            )
                        )

                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(modifier = Modifier.width(8.dp))


                Box(
                    modifier = Modifier
                        .offset(y = (-20).dp)
                        .width(37.dp)
                        .height(18.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(Color(0xFFFF5A08)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "PTS", color = Color.White, style = TextStyle(
                            fontFamily = SpaceGrotesk,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            lineHeight = 14.sp,
                            letterSpacing = 0.sp
                        ), textAlign = TextAlign.Center
                    )
                }


            }
        }
    }
}