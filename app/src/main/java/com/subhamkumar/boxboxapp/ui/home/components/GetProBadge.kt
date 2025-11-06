package com.subhamkumar.boxboxapp.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.subhamkumar.boxboxapp.R


@Composable
fun GetProBadge(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(12.dp)
            .background(
                color = Color.White.copy(alpha = 0.12f), shape = RoundedCornerShape(15.dp)
            )
            .padding(horizontal = 10.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.diamond_ig),
            contentDescription = "Pro Badge",
            modifier = Modifier.size(18.dp)
        )

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            text = "Get Pro",
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}