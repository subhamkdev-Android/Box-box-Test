package com.subhamkumar.boxboxapp.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriverDetailsScreen(driverId: String?) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Driver Details") })
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            if (driverId != null) {
                Text(
                    text = "Driver ID: $driverId",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            } else {
                Text(
                    text = "No driver selected",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
