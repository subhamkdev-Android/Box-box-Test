package com.subhamkumar.boxboxapp.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.subhamkumar.boxboxapp.R


@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    initialSelectedIndex: Int = 0,
    onFirstIconClick: (() -> Unit)? = null,
    navHeight: Dp = 84.dp
) {
    var selectedIndex by remember { mutableStateOf(initialSelectedIndex) }

    val inactiveIconTint = Color.Unspecified
    val barBg = Color(0xFF000000)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(navHeight)
            .navigationBarsPadding(),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(barBg)
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            if (selectedIndex == 0) Color.White.copy(alpha = 0.12f) else Color.Transparent
                        )
                        .clickable {
                            selectedIndex = 0
                            onFirstIconClick?.invoke()
                        }) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(if (selectedIndex == 0) Color.White else Color(0xFF1F1F1F)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_home),
                            contentDescription = "Home",
                            tint = if (selectedIndex == 0) Color.Black else Color(0xFF9E9E9E),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }


                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar_days),
                    contentDescription = "Calendar",
                    tint = inactiveIconTint,
                    modifier = Modifier.size(28.dp)
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_trophy),
                    contentDescription = "Trophy",
                    tint = inactiveIconTint,
                    modifier = Modifier.size(28.dp)
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_globus),
                    contentDescription = "Globe",
                    tint = inactiveIconTint,
                    modifier = Modifier.size(28.dp)
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = "Profile",
                    tint = inactiveIconTint,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}