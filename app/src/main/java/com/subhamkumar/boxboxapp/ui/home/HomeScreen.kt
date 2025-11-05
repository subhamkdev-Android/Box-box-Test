package com.subhamkumar.boxboxapp.ui.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import com.subhamkumar.boxboxapp.R
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.subhamkumar.boxboxapp.data.model.Driver
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel: HomeViewModel = koinViewModel()

    val drivers by viewModel.drivers.collectAsState()
    val races by viewModel.races.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    val pagerState = rememberPagerState(pageCount = { 2 })

    // Auto-slide every 3 seconds
    LaunchedEffect(pagerState) {
        while (true) {
            delay(3000)
            val nextPage = (pagerState.currentPage + 1) % 2
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            isLoading -> ExpressiveLoader()

            error != null -> Text(
                text = "Error: $error",
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center
            )

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // 🔹 Horizontal Pager for sliding cards
                    item {
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                                .padding(0.dp,0.dp)


                        ) { page ->
                            when (page) {
                                0 -> {
                                    if (drivers.isNotEmpty()) {
                                        val topDriver = drivers.first()
                                        TopDriverCard(
                                            driver = topDriver,
                                            navController = navController
                                        )
                                    } else {
                                        Text("No driver data available")
                                    }
                                }

                                1 -> {
                                    TwoImageSlideCard()
                                }
                            }
                        }
                    }

                    item { Spacer(modifier = Modifier.height(24.dp)) }

                    // 🔹 Upcoming Race Section
                    item {
                        if (races.isNotEmpty()) {
                            val nextRace = races.first()
                            Text(
                                text = "Upcoming Race: ${nextRace.raceName}",
                                fontSize = 18.sp,
                                textAlign = TextAlign.Center
                            )
                        } else {
                            Text("No race data available")
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun TopDriverCard(
    driver: Driver,
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .clip(RoundedCornerShape(0.16.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFF6F00), // bright orange
                        Color(0xFFFF6F00), // keep orange bulk
                        Color(0xFF000000)  // black bottom quarter
                    )
                )
            )
            .clickable { navController.navigate("driver_details/${driver.driverId}") }
    ) {
        Text(
            text = driver.firstName.uppercase(),
            fontSize = 96.sp,
            color = Color.White.copy(alpha = 0.20f),
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .align(Alignment.TopStart)
                .absoluteOffset(20.dp, 20.dp)
                .padding(start = 26.dp, top = 16.dp)
        )

        // Driver image - lower and slightly zoomed, crop showing hands/body
        Image(
            painter = painterResource(id = R.drawable.lando),
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
                            Color.Transparent,
                            Color(0xE6000000) // semi-black at bottom (90% opacity)
                        ),
                        startY = 400f, // start fading lower part only
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        )

        // Bottom overlay content
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 20.dp, bottom = 22.dp)
        ) {
            // Position & Wins row
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.position_icon),
                    contentDescription = "position icon",
                    modifier = Modifier.size(14.dp)
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = "${driver.position} Pos",
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.width(12.dp))

                Image(
                    painter = painterResource(id = R.drawable.icon),
                    contentDescription = "wins icon",
                    modifier = Modifier.size(14.dp)
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = "${driver.wins} Wins",
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Points section with gradient text + solid orange PTS box
            Row(verticalAlignment = Alignment.Bottom) {
                // Gradient "points" text
                Text(
                    text = driver.points.toString(),
                    fontSize = 64.sp,
                    fontWeight = FontWeight.Light,
                    letterSpacing = (-1).sp,
                    style = LocalTextStyle.current.copy(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFFFFA726), // light orange
                                Color(0xFFFFFFFF)  // white gradient top
                            )
                        )
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Solid vivid orange "PTS" tag
                Text(
                    text = "PTS",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .offset(y = (-8).dp)
                        .background(
                            color = Color(0xFFFF9800), // vivid orange solid
                            shape = RoundedCornerShape(6.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
fun ExpressiveLoader() {
    val infiniteTransition = rememberInfiniteTransition(label = "loader")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing)
        ),
        label = "rotation"
    )

    Box(
        modifier = Modifier
            .size(80.dp)
            .rotate(angle),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            strokeWidth = 6.dp,
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}

@Composable
fun TwoImageSlideCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .clip(RoundedCornerShape(0.1.dp))
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.second_screen_img),
                contentDescription = "Top Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp)
                    .padding(top = 56.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 20.dp,
                            topEnd = 20.dp
                        )
                    )
            )

            Image(
                painter = painterResource(id = R.drawable.second_screen_img_bt),
                contentDescription = "Bottom Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(bottom = 16.dp)
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 20.dp,
                            bottomEnd = 20.dp
                        )
                    )
            )
        }
    }
}




