package com.subhamkumar.boxboxapp.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.foundation.layout.defaultMinSize
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
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import com.subhamkumar.boxboxapp.R
import com.subhamkumar.boxboxapp.data.model.Driver
import com.subhamkumar.boxboxapp.data.model.Race
import com.subhamkumar.boxboxapp.data.model.Session
import com.subhamkumar.boxboxapp.ui.theme.SpaceGrotesk
import com.subhamkumar.boxboxapp.ui.theme.montserratFontFamily
import com.subhamkumar.boxboxapp.utils.formatAmPm
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
                .weight(1f)
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
                imageRes = R.drawable.ic_distance_icon,
                progress = 0.38f,
                animateProgress = true
            )

            LinkCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                iconRes = R.drawable.ic_medium,
                titleTop = "Formula 1",
                titleBottom = "Education",
                url = "https://blog.boxbox.club/tagged/beginners-guide",
                topRightIcon = R.drawable.ic_arrow_right
            )
        }
    }
}


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


@Composable
fun StatCard(
    modifier: Modifier = Modifier,
    label: String = "7015.3",
    unit: String = "km",
    @DrawableRes imageRes: Int = R.drawable.ic_distance_icon,
    progress: Float = 0.40f,
    animateProgress: Boolean = true,
    fontFamily: FontFamily = SpaceGrotesk
) {
    val clamped = progress.coerceIn(0f, 1f)
    val target = if (animateProgress) clamped else clamped
    val animatedProgress by animateFloatAsState(
        targetValue = target, animationSpec = tween(
            durationMillis = 500, easing = FastOutSlowInEasing
        )
    )

    val cardRadius = 16.dp
    val redColor = Color(0xFFF51A1E)
    val blackPanel = Color(0xFF0B0B0B)
    val outline = Color(0xFF212121)

    Card(
        modifier = modifier
            .height(64.dp)
            .clip(RoundedCornerShape(cardRadius)),
        shape = RoundedCornerShape(cardRadius),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(blackPanel)
                .padding(1.dp)
        ) {
            Box(modifier = Modifier.matchParentSize()) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(animatedProgress)
                        .clip(RoundedCornerShape(topStart = cardRadius, bottomStart = cardRadius))
                        .background(
                            Brush.horizontalGradient(listOf(redColor, redColor.copy(alpha = 0.95f)))
                        )
                )

                if (animatedProgress in 0.001f..0.999f) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(animatedProgress)
                    ) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .width(1.dp)
                                .fillMaxHeight()
                                .background(outline.copy(alpha = 0.6f))
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 9.dp, end = 9.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = "stat icon",
                        modifier = Modifier.size(28.dp),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = label, style = TextStyle(
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp,
                                lineHeight = 28.sp,
                            ), color = Color.White, maxLines = 1
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Box(
                            modifier = Modifier
                                .height(28.dp)
                                .defaultMinSize(minWidth = 36.dp)
                                .padding(top = 8.dp)
                                .clip(RoundedCornerShape(6.dp)),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = unit, style = TextStyle(
                                    fontFamily = fontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp,
                                    lineHeight = 28.sp,
                                    letterSpacing = 0.sp
                                ), color = Color.White, maxLines = 1, textAlign = TextAlign.Start
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .padding(0.5.dp)
                    .clip(RoundedCornerShape(cardRadius))
            ) {}
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
                    painter = painterForDrawable(iconRes),
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
                    painter = painterForDrawable(topRightIcon),
                    contentDescription = "Open link icon",
                    modifier = Modifier.size(18.dp),
                )
            }
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


    LaunchedEffect(pagerState) {
        while (true) {
            delay(3000)
            val nextPage = (pagerState.currentPage + 1) % 2
            pagerState.animateScrollToPage(nextPage)
        }
    }

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
                        Box {
                            GetProBadge(
                                modifier = Modifier
                                    .padding(top = 40.dp)
                                    .zIndex(1f)
                            )
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
                                //todo
                                PagerIndicator(
                                    pagerState = pagerState,
                                    pageCount = 2,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .padding(bottom = 0.dp)
                                )
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
fun GetProBadge(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(12.dp)
            .background(
                color = Color.Transparent, shape = RoundedCornerShape(12.dp)
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
                painter = painterResource(id = R.drawable.ic_slider_bg),
                contentDescription = "Top Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
                    .padding(top = 120.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 20.dp, topEnd = 20.dp
                        )
                    )
            )

            FollowButton(
                modifier = Modifier,  label = "Follow Us", onClick = { /* handle click */ })
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
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/boxbox_club/")
                )
                context.startActivity(intent)
            },
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "F1 25 Instagram card",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentScale = ContentScale.FillWidth
            )

            Image(
                painter = painterResource(id = R.drawable.ic_insta),
                contentDescription = "Instagram logo",
                modifier = Modifier
                    .size(28.dp)
                    .padding(end = 10.dp, bottom = 10.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}



@Composable
fun PagerIndicator(
    pagerState: PagerState,
    pageCount: Int,
    modifier: Modifier = Modifier,
    activeColor: Color = Color.White,
    inactiveColor: Color = Color.White.copy(alpha = 0.35f),
    activeWidth: Dp = 28.dp,
    inactiveWidth: Dp = 6.dp,
    height: Dp = 6.dp,
    gap: Dp = 8.dp
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(gap),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val current = pagerState.currentPage
        for (i in 0 until pageCount) {
            val isActive = i == current
            val targetWidth = if (isActive) activeWidth else inactiveWidth
            val animatedWidth by animateDpAsState(targetValue = targetWidth)
            val animatedColor by animateColorAsState(targetValue = if (isActive) activeColor else inactiveColor)

            Box(
                modifier = Modifier
                    .size(width = animatedWidth, height = height)
                    .clip(RoundedCornerShape(percent = 50))
                    .background(animatedColor)
            )
        }
    }
}
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



