package com.subhamkumar.boxboxapp.ui.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel: HomeViewModel = koinViewModel()

    val drivers by viewModel.drivers.collectAsState()
    val races by viewModel.races.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            isLoading -> {
                ExpressiveLoader()
            }

            error != null -> {
                Text(
                    text = "Error: $error",
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center
                )
            }

            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Top Driver",
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    if (drivers.isNotEmpty()) {
                        val topDriver = drivers.first()
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = topDriver.fullName, fontSize = 18.sp)
                        Text(text = "Team: ${topDriver.teamName}")
                        Text(text = "Points: ${topDriver.points}")
                    } else {
                        Text(text = "No driver data available")
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    if (races.isNotEmpty()) {
                        val nextRace = races.first()
                        Text(
                            text = "Upcoming Race: ${nextRace.raceName}",
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )
                    } else {
                        Text(text = "No race data available")
                    }
                }
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


