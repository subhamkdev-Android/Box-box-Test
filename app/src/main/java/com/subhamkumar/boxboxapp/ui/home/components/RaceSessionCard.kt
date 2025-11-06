package com.subhamkumar.boxboxapp.ui.home.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.subhamkumar.boxboxapp.R
import com.subhamkumar.boxboxapp.data.model.Session
import com.subhamkumar.boxboxapp.ui.theme.SpaceGrotesk
import com.subhamkumar.boxboxapp.utils.formatAmPm
import com.subhamkumar.boxboxapp.utils.formatDateLocal
import com.subhamkumar.boxboxapp.utils.formatTimeLocal


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RaceSessionCard(
    modifier: Modifier = Modifier, session: Session, onClick: () -> Unit
) {
    val greenBg = Color(0xFF044331)
    Color(0xFF2FE09B)
    val whiteAlpha = Color.White.copy(alpha = 0.95f)

    Card(
        modifier = modifier.clickable { onClick() }, shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(greenBg)
                .padding(14.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_circuit),
                contentDescription = "Circuit Brazil",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(46.dp)
                    .align(Alignment.TopEnd)
            )

            Column(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(top = 15.dp)
            ) {

                Text(
                    text = session.sessionName,
                    color = whiteAlpha,
                    fontWeight = FontWeight.SemiBold,
                    style = TextStyle(
                        fontFamily = SpaceGrotesk,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                        letterSpacing = 0.sp,
                    ),
                    modifier = Modifier
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                        .clip(RoundedCornerShape(4.dp))
                )

                Spacer(modifier = Modifier.height(6.dp))


                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_calender),
                        contentDescription = "date icon",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(18.dp)
                            .padding(start = 6.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = formatDateLocal(session.startTime),
                        color = whiteAlpha,
                        fontSize = 14.sp,
                        style = TextStyle(
                            fontFamily = SpaceGrotesk,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 18.sp,
                        )
                    )
                }

                val time = formatTimeLocal(session.startTime)
                val unitTime = formatAmPm(session.startTime)

                Spacer(modifier = Modifier.height(6.dp))
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = time, style = TextStyle(
                            fontFamily = SpaceGrotesk,
                            fontWeight = FontWeight.Bold,
                            fontSize = 36.sp,
                            lineHeight = 36.sp,
                            color = Color(0xFF02BB81)
                        )
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = unitTime, style = TextStyle(
                            fontFamily = SpaceGrotesk,
                            fontWeight = FontWeight.Medium,
                            fontSize = 20.sp,
                            lineHeight = 20.sp,
                            color = Color(0xFF02BB81)
                        )
                    )
                }
            }
        }
    }
}