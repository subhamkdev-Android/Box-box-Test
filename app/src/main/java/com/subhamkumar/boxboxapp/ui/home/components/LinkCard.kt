package com.subhamkumar.boxboxapp.ui.home.components


import android.content.Intent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.subhamkumar.boxboxapp.ui.theme.montserratFontFamily


@Composable
fun LinkCard(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int,
    titleTop: String,
    titleBottom: String,
    url: String,
    @DrawableRes topRightIcon: Int
) {
    val ctx = LocalContext.current
    val deepBlue = Color(0xFF3B2BFF)

    Card(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .height(60.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(deepBlue)
            .clickable {
                ctx.startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
            }

        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 14.dp)
                    .align(Alignment.CenterStart), verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(iconRes),
                    contentDescription = "Link icon",
                    modifier = Modifier
                        .size(32.dp)
                        .padding(end = 10.dp)
                        .alpha(1f),
                    contentScale = ContentScale.Fit
                )



                Column(
                    verticalArrangement = Arrangement.Center, modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = titleTop, color = Color(0xFFFFFFFF), style = TextStyle(
                            fontFamily = montserratFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            letterSpacing = 0.sp,
                        ), maxLines = 1, overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = titleBottom, color = Color(0xFFFFFFFF), style = TextStyle(
                            fontFamily = montserratFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            lineHeight = 16.sp,
                            letterSpacing = 0.sp,
                        ), maxLines = 1, overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .clip(CircleShape)
                    .padding(8.dp),

                ) {
                Image(
                    painter = painterResource(topRightIcon),
                    contentDescription = "Open link icon",
                    modifier = Modifier.size(18.dp),
                )
            }
        }
    }
}