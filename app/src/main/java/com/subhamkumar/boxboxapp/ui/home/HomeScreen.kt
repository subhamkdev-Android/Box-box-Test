package com.subhamkumar.boxboxapp.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.subhamkumar.boxboxapp.R
import com.subhamkumar.boxboxapp.data.model.Driver
import com.subhamkumar.boxboxapp.data.model.Race
import com.subhamkumar.boxboxapp.data.model.Session
import com.subhamkumar.boxboxapp.utils.formatDateLocal
import com.subhamkumar.boxboxapp.utils.formatTimeLocal
import com.subhamkumar.boxboxapp.utils.getNextUpcomingRace
import com.subhamkumar.boxboxapp.utils.getNextUpcomingSession
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun RaceSummaryRow(
    race: Race?, session: Session?, onClickRaceInfo: () -> Unit
) {
    if (race == null || session == null) return

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        RaceSessionCard(
            modifier = Modifier
                .weight(1.6f)
                .height(132.dp),
            session = session,
            onClick = onClickRaceInfo
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .height(132.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StatCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                label = "7015.3",
                unit = "km",
                imageRes = R.drawable.icon_distance // your left image
            )

            LinkCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                iconRes = R.drawable.medium,
                titleTop = "Formula 1",
                titleBottom = "Education",
                url = "https://blog.boxbox.club/tagged/beginners-guide",
                topRightIcon = R.drawable.frame
            )
        }
    }
}


@Composable
fun RaceSessionCard(
    modifier: Modifier = Modifier, session: Session, onClick: () -> Unit
) {
    val greenBg = Color(0xFF0D624F)
    val timeGreen = Color(0xFF2FE09B)
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
            // top-right small track icon
            Icon(
                painter = painterResource(id = R.drawable.circuit),
                contentDescription = "Circuit",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(56.dp)
                    .align(Alignment.TopEnd)
            )

            Column(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(end = 36.dp, top = 15.dp) // leave room for the image/icon at right
            ) {

                Text(
                    text = session.sessionName,
                    color = whiteAlpha,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Small line with calendar icon + date
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.vector),
                        contentDescription = "date icon",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(18.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = formatDateLocal(session.startTime),
                        color = whiteAlpha,
                        fontSize = 13.sp
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Big time
                Text(
                    text = formatTimeLocal(session.startTime),
                    color = timeGreen,
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun StatCard(
    modifier: Modifier = Modifier,
    label: String = "7015.3",
    unit: String = "km",
    @DrawableRes imageRes: Int,
    @DrawableRes fallbackBitmap: Int? = null
) {
    // tweak this to control how wide the left image pill appears
    val leftImageWidth = 22.dp

    Card(
        modifier = modifier
            .height(64.dp)
            .clip(RoundedCornerShape(0.dp)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            val leftPainter: Painter = runCatching { painterForDrawable(imageRes) }.getOrElse {
                fallbackBitmap?.let { painterResource(id = it) } ?: painterResource(id = imageRes)
            }

//            Image(
//                painter = leftPainter,
//                contentDescription = "stat left image",
//                modifier = Modifier
//                    .fillMaxHeight()
//                    .width(leftImageWidth)
//                    .clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)),
//                contentScale = ContentScale.None
//            )

            // Right panel must expand — use weight so it doesn't squeeze the text
            Box(
                modifier = Modifier
                    .background(Color(0xFF0B0B0B))
                    .height(64.dp)
                    .padding(start = 14.dp, end = 12.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = label,
                        color = Color.White,
                        // slightly reduced so it fits better
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        softWrap = false
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = unit,
                        color = Color.White.copy(alpha = 0.85f),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

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
            .height(60.dp), // consistent height
        shape = RoundedCornerShape(16.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(deepBlue)
                .clickable {
                    ctx.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                }
                .padding(horizontal = 14.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterStart),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Left icon (Formula 1 dots)
                Image(
                    painter = painterForDrawable(iconRes),
                    contentDescription = "Link icon",
                    modifier = Modifier
                        .size(28.dp)
                        .padding(end = 10.dp),
                    contentScale = ContentScale.Fit
                )

                // Texts
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = titleTop,
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = titleBottom,
                        color = Color.White,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

//            // Top-right arrow inside card (smaller, properly positioned)
//            Box(
//                modifier = Modifier
//                    .size(22.dp)
//                    .align(Alignment.TopEnd)
//                    .offset(x = (-10).dp, y = 8.dp)
//                    .clip(CircleShape)
//                    .background(Color.White),
//                contentAlignment = Alignment.Center
//            ) {
//                Image(
//                    painter = painterForDrawable(topRightIcon),
//                    contentDescription = "Open link icon",
//                    modifier = Modifier.size(10.dp),
//                    contentScale = ContentScale.Fit
//                )
//            }
        }
    }
}




@RequiresApi(Build.VERSION_CODES.O)
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

    // Entire screen background black
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
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
                        .fillMaxSize()
                        .background(Color.Black),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(400.dp)
                                .padding(0.dp, 0.dp)
                        ) { page ->
                            when (page) {
                                0 -> {
                                    if (drivers.isNotEmpty()) {
                                        val topDriver = drivers.first()
                                        TopDriverCard(
                                            driver = topDriver, navController = navController
                                        )
                                    } else {
                                        Text("No driver data available", color = Color.White)
                                    }
                                }

                                1 -> {
                                    TwoImageSlideCard()
                                }
                            }
                        }
                    }

                    item { Spacer(modifier = Modifier.height(24.dp)) }
                    item {
                        if (races.isNotEmpty()) {
                            val nextRace = getNextUpcomingRace(races)
                            val nextSession = nextRace?.sessions?.let { getNextUpcomingSession(it) }

                            if (nextRace != null && nextSession != null) {
                                RaceSummaryRow(
                                    race = nextRace, session = nextSession, onClickRaceInfo = {
                                        navController.navigate("race_detail/${nextRace.raceId}")
                                    })
                            } else {
                                Text(
                                    text = "No upcoming race session found",
                                    color = Color.Gray,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        } else {
                            Text(text = "No race data available", color = Color.Gray)
                        }
                    }
                    item { Spacer(modifier = Modifier.height(20.dp)) }
                    item {
                        InstagramCard()
                    }
                    item { Spacer(modifier = Modifier.height(20.dp)) }

                }
            }
        }
    }
}

@Composable
fun GetProBadge() {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .background(
                color = Color.Black.copy(alpha = 0.4f), shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 10.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.diamond_ig),
            contentDescription = "Pro Badge", modifier = Modifier.size(18.dp)
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
        //  .clickable { navController.navigate("driver_details/${driver.driverId}") }
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

        GetProBadge()
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
                            Color.Transparent, Color(0xE6000000)
                        ), startY = 400f, endY = Float.POSITIVE_INFINITY
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
                    painter = painterResource(id = R.drawable.icons),
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

                Text(
                    text = "PTS",
                    color = Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .offset(y = (-8).dp)
                        .width(35.dp)
                        .height(18.dp)
                        .background(
                            color = Color(0xFFFF5A08), // Updated to deep orange-red #FF5A08
                            shape = RoundedCornerShape(5.dp)
                        )
                        .padding(
                            start = 8.dp,
                            top = 2.dp,
                            end = 0.dp,
                            bottom = 2.dp
                        )
                )

            }
        }
    }
}

@Composable
fun ExpressiveLoader() {
    val infiniteTransition = rememberInfiniteTransition(label = "loader")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 360f, animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing)
        ), label = "rotation"
    )

    Box(
        modifier = Modifier
            .size(80.dp)
            .rotate(angle), contentAlignment = Alignment.Center
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
            .height(400.dp)
            .clip(RoundedCornerShape(0.1.dp))
            .background(Color.Black), contentAlignment = Alignment.Center
    ) {
        GetProBadge()
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
                    .height(270.dp)
                    .padding(top = 90.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 20.dp, topEnd = 20.dp
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
                            bottomStart = 20.dp, bottomEnd = 20.dp
                        )
                    )
            )
        }
    }
}


@Composable
private fun painterForDrawable(@DrawableRes id: Int): Painter {
    return painterResource(id = id)
}



@Composable
fun InstagramCard(imageRes: Int = R.drawable.image_insta) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/boxbox_club/"))
                context.startActivity(intent)
            },
        shape = RoundedCornerShape(16.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "F1 25 Instagram card",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentScale = ContentScale.FillWidth // scale image to fit width and keep aspect ratio
        )
    }
}





