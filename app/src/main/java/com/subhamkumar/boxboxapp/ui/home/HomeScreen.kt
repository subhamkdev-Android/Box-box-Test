package com.subhamkumar.boxboxapp.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.subhamkumar.boxboxapp.ui.home.components.BottomNavBar
import com.subhamkumar.boxboxapp.ui.home.components.ExpressiveLoader
import com.subhamkumar.boxboxapp.ui.home.components.GetProBadge
import com.subhamkumar.boxboxapp.ui.home.components.InstagramCard
import com.subhamkumar.boxboxapp.ui.home.components.PagerIndicator
import com.subhamkumar.boxboxapp.ui.home.components.RaceSummaryRow
import com.subhamkumar.boxboxapp.ui.home.components.TopDriverCard
import com.subhamkumar.boxboxapp.ui.home.components.TwoImageSlideCard
import com.subhamkumar.boxboxapp.utils.getNextUpcomingRace
import com.subhamkumar.boxboxapp.utils.getNextUpcomingSession
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel


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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),

        ) {
        when {
            isLoading -> ExpressiveLoader(
                modifier = Modifier.fillMaxSize()
            )

            error != null -> Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Error: $error",
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center
                )
            }


            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
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
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
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
                                                    driver = topDriver,
                                                    navController = navController
                                                )
                                            } else {
                                                Text(
                                                    "No driver data available", color = Color.White
                                                )
                                            }
                                        }

                                        1 -> {
                                            TwoImageSlideCard()
                                        }
                                    }
                                }

                                PagerIndicator(
                                    pagerState = pagerState,
                                    pageCount = 2,
                                    modifier = Modifier.padding(top = 8.dp)

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
                BottomNavBar(
                    modifier = Modifier
                )
            }
        }
    }
}



