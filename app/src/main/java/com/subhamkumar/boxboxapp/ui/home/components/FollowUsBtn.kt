package com.subhamkumar.boxboxapp.ui.home.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.subhamkumar.boxboxapp.ui.theme.montserratFontFamily


@Composable
fun FollowButton(
    modifier: Modifier = Modifier,
    label: String = "Follow Us",
    fontFamily: FontFamily = montserratFontFamily,
    onClick: () -> Unit = {}
) {
    val bgColor = Color(0xFF86FF0E)

    Box(
        modifier = modifier
            .width(140.dp)
            .height(44.dp)
            .clip(RoundedCornerShape(55.dp))
            .background(bgColor)
            .clickable { onClick() }) {
        Row(
            modifier = Modifier
                .matchParentSize()
                .padding(start = 19.dp, end = 19.dp, top = 13.dp, bottom = 13.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = label, textAlign = TextAlign.Center, style = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    color = Color.Black
                )
            )
        }
    }
}