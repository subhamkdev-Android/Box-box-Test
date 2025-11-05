package com.subhamkumar.boxboxapp.ui.details

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RaceDetailScreen(
    navController: NavHostController,
    raceId: String?
) {
    // ---------- Helpers & placeholder data ----------
    fun toMillis(epoch: Long): Long =
        if (epoch < 1_000_000_000_000L) epoch * 1000L else epoch

    // exact placeholder paragraph you provided
    val circuitTitle = "São Paulo Circuit"
    val circuitParagraph =
        "Bahrain International circuit is located in Sakhir, Bahrain and it was designed by German architect Hermann Tilke. " +
                "It was built on the site of a former camel farm, in Sakhir. It measures 5.412 km, has 15 corners and 3 DRS Zones. " +
                "The Grand Prix have 57 laps. This circuit has 6 alternative layouts."

    // facts as paragraphs (no bullets)
    val factParagraphs = listOf(
        "His brother Arthur Leclerc is currently set to race for DAMS in the 2023 F2 Championship",
        "He’s not related to Édouard Leclerc, the founder of a French supermarket chain"
    )

    // header placeholder texts
    val roundText = "Round 12"
    val raceName = "São Paulo GP"
    val circuitIdText = "São Paulo"
    val dateRangeText = "23 - 30 April"

    // sample start time for countdown (7 days from now)
    val sampleStartMillis = System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000L

    // countdown state updates every second
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

    // ---------- UI ----------
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentPadding = PaddingValues(bottom = 56.dp)
    ) {
        item {
            // Full-width header with three-stop gradient (dark -> mid -> black)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF073E33), // dark green (start)
                                Color(0xFF0F7A56), // mid green
                                Color(0xFF041811)  // near-black at bottom
                            ),
                            startY = 0f,
                            endY = Float.POSITIVE_INFINITY
                        )
                    )
                    .padding(horizontal = 20.dp)
            ) {
                // top title pushed slightly down
                Text(
                    text = "Upcoming race",
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 28.dp), // moved down a bit
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )

                // left column with round/race/circuit/date (reduced race name size)
                Column(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 4.dp, top = 72.dp)
                ) {
                    Text(text = roundText, color = Color.White.copy(alpha = 0.9f), fontSize = 14.sp)
                    Spacer(Modifier.height(6.dp))
                    Text(text = raceName, color = Color.White, fontSize = 36.sp, fontWeight = FontWeight.Bold) // slightly smaller
                    Spacer(Modifier.height(6.dp))
                    Text(text = circuitIdText, color = Color(0xFF7EDCB1), fontSize = 14.sp)
                    Spacer(Modifier.height(6.dp))
                    Text(text = dateRangeText, color = Color.White.copy(alpha = 0.9f), fontSize = 14.sp)
                }

                // 3D circuit image moved slightly down and right
                Image(
                    painter = painterResource(id = R.drawable.td_circuit), // replace with actual id
                    contentDescription = "3d circuit",
                    modifier = Modifier
                        .size(190.dp)
                        .align(Alignment.TopEnd)
                        .offset(x = (-12).dp, y = 28.dp), // pushed down
                    contentScale = ContentScale.Fit
                )

                // FP1 Starts in - inline large numbers (no boxes)
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 4.dp, bottom = 18.dp)
                ) {
                    Text(text = "FP1 Starts in", color = Color.White.copy(alpha = 0.9f), fontSize = 12.sp)

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Days
                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(end = 20.dp)) {
                            Text(text = two(days), color = Color(0xFF2FE09B), fontSize = 30.sp, fontWeight = FontWeight.Bold)
                            Text(text = "Days", color = Color.White.copy(alpha = 0.85f), fontSize = 12.sp)
                        }

                        // Hours
                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(end = 20.dp)) {
                            Text(text = two(hours), color = Color(0xFF2FE09B), fontSize = 30.sp, fontWeight = FontWeight.Bold)
                            Text(text = "Hours", color = Color.White.copy(alpha = 0.85f), fontSize = 12.sp)
                        }

                        // Minutes
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = two(minutes), color = Color(0xFF2FE09B), fontSize = 30.sp, fontWeight = FontWeight.Bold)
                            Text(text = "Minutes", color = Color.White.copy(alpha = 0.85f), fontSize = 12.sp)
                        }
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(18.dp)) }

        // Circuit title
        item {
            Text(
                text = circuitTitle,
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }

        // Circuit paragraph (white text, with subtle rounded strip around)
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black)
                        .padding(14.dp)
                ) {
                    Text(
                        text = circuitParagraph,
                        color = Color.White.copy(alpha = 0.95f),
                        fontSize = 16.sp,
                        lineHeight = 22.sp
                    )
                }
            }
        }

        // Circuit Facts header
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Circuit Facts",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
            )
        }

        // Facts paragraphs (no bullets). After each fact add a subtle transparent divider
        items(factParagraphs.size) { idx ->
            val p = factParagraphs[idx]
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = p,
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 16.sp,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                // subtle transparent divider
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
