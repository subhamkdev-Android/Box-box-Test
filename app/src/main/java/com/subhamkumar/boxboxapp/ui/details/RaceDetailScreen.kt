package com.subhamkumar.boxboxapp.ui.details

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.subhamkumar.boxboxapp.R
import kotlinx.coroutines.delay
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.subhamkumar.boxboxapp.ui.theme.montserratFontFamily

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RaceDetailScreen(
    navController: NavHostController,
    raceId: String?
) {

    fun toMillis(epoch: Long): Long =
        if (epoch < 1_000_000_000_000L) epoch * 1000L else epoch

    val circuitTitle = "São Paulo Circuit"
    val circuitParagraph =
        "Bahrain International circuit is located in Sakhir, Bahrain and it was designed by German architect Hermann Tilke. " +
                "It was built on the site of a former camel farm, in Sakhir. It measures 5.412 km, has 15 corners and 3 DRS Zones. " +
                "The Grand Prix have 57 laps. This circuit has 6 alternative layouts."

    val factParagraphs = listOf(
        "His brother Arthur Leclerc is currently set to race for DAMS in the 2023 F2 Championship",
        "He’s not related to Édouard Leclerc, the founder of a French supermarket chain"
    )

    val roundText = "Round 12"
    val raceName = "São Paulo GP"
    val circuitIdText = "São Paulo"
    val dateRangeText = "23 - 30 April"

    // sample start (7 days from now)
    val sampleStartMillis = System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000L

    var now by remember { mutableStateOf(System.currentTimeMillis()) }
    LaunchedEffect(Unit) {
        while (true) {
            now = System.currentTimeMillis()
            delay(1000L)
        }
    }
    val remaining = (toMillis(sampleStartMillis) - now).coerceAtLeast(0L)
    val days = (remaining / (24 * 60 * 60 * 1000)).toInt()
    val hours = ((remaining / (60 * 60 * 1000)) % 24).toInt()
    val minutes = ((remaining / (60 * 1000)) % 60).toInt()
    fun two(v: Int) = if (v < 10) "0$v" else v.toString()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentPadding = PaddingValues(bottom = 56.dp)
    ) {
        item {
            // Header box: background image fills entire box (no padding)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                // full-bleed background image (no padding)
                Image(
                    painter = painterResource(id = R.drawable.bg_detail),
                    contentDescription = "Background",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .matchParentSize()
                )

                // Optional subtle overlay to ensure text contrast (tweak alpha as needed)
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(Color.Black.copy(alpha = 0.18f))
                )

                Text(
                    text = "Upcoming race",
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 40.dp, bottom = 50.dp),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 16.04.sp,
                    lineHeight = 16.04.sp, // 100% of font size
                    style = TextStyle(
                        fontFamily = montserratFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.04.sp,
                        lineHeight = 16.04.sp,
                        letterSpacing = 0.sp
                    )
                )


                // Left column for round, name, etc. (has its own padding)
                Column(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 20.dp, top = 102.dp)
                ) {
                    Text(text = roundText, color = Color.White.copy(alpha = 0.9f), fontSize = 14.sp, fontWeight = FontWeight.SemiBold, fontFamily = montserratFontFamily)
                    Spacer(Modifier.height(6.dp))
                    Text(text = raceName, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold, fontFamily = montserratFontFamily)
                    Spacer(Modifier.height(6.dp))
                    Text(text = circuitIdText, color = Color(0xFF009B3A), fontSize = 14.sp, fontWeight = FontWeight.SemiBold, fontFamily = montserratFontFamily)
                    Spacer(Modifier.height(6.dp))
                    Text(text = dateRangeText, color = Color.White.copy(alpha = 0.9f), fontSize = 14.sp, fontWeight = FontWeight.SemiBold, fontFamily = montserratFontFamily)
                }

                // 3D circuit image: sized 180 x 180 and positioned slightly down.
                // Using absoluteOffset so it's placed relative to the header's top-left.
                Image(
                    painter = painterResource(id = R.drawable.td_circuit),
                    contentDescription = "3d circuit",
                    modifier = Modifier
                        .size(180.dp) // width & height ~ 180dp as requested
                        .absoluteOffset(x = 195.dp, y = 116.dp),
                    contentScale = ContentScale.Fit
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 20.dp, bottom = 18.dp)
                ) {
                    Text(text = "FP1 Starts in", color = Color.White, fontSize = 10.sp, fontFamily = montserratFontFamily, fontWeight = FontWeight.W400)

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(end = 20.dp)) {
                            Text(text = two(days), color = Color(0xFF009B3A), fontSize = 30.sp, fontWeight = FontWeight.Bold)
                            Text(text = "Days", color = Color.White.copy(alpha = 0.85f), fontSize = 8.sp)
                        }

                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(end = 20.dp)) {
                            Text(text = two(hours), color = Color(0xFF009B3A), fontSize = 30.sp, fontWeight = FontWeight.Bold)
                            Text(text = "Hours", color = Color.White.copy(alpha = 0.85f), fontSize = 8.sp)
                        }

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = two(minutes), color = Color(0xFF009B3A), fontSize = 30.sp, fontWeight = FontWeight.Bold)
                            Text(text = "Minutes", color = Color.White.copy(alpha = 0.85f), fontSize = 8.sp)
                        }
                    }
                }
            }
        }

        // slight transition strip to content (keeps your gradient look)
        item {
            Spacer(
                modifier = Modifier
                    .height(18.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF041811),
                                Color(0xFF000000)
                            ),
                            startY = 0f,
                            endY = Float.POSITIVE_INFINITY
                        )
                    )
            )
        }

        item {
            Text(
                text = circuitTitle,
                color = Color.White,
                style = TextStyle(
                    fontFamily = montserratFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    lineHeight = 18.sp,
                    letterSpacing = 0.sp
                ),
                modifier = Modifier.padding(horizontal = 20.dp)
            )

        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black)
                        .padding(0.dp)
                ) {
                    Text(
                        text = circuitParagraph,
                        color = Color.White,
                        lineHeight = 22.sp,
                        style = TextStyle(
                            fontFamily = montserratFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            lineHeight = 18.sp,
                            letterSpacing = 0.sp
                        )
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Circuit Facts",
                style = TextStyle(
                    fontFamily = montserratFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    lineHeight = 18.sp,
                    letterSpacing = 0.sp
                ),
                color = Color.White,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        items(factParagraphs.size) { idx ->
            val p = factParagraphs[idx]
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = p,
                    color = Color.White.copy(alpha = 0.9f),
                    style = TextStyle(
                        fontFamily = montserratFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        letterSpacing = 0.sp
                    ),
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .alpha(0.06f)
                        .background(Color.White.copy(alpha = 0.06f))
                )

                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        item { Spacer(modifier = Modifier.height(64.dp)) }
    }
}
