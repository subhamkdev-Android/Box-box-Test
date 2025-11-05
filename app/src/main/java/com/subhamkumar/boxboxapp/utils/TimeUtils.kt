package com.subhamkumar.boxboxapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.subhamkumar.boxboxapp.data.model.Race
import com.subhamkumar.boxboxapp.data.model.Session
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun getNextUpcomingRace(races: List<Race>): Race? {
    val now = Instant.now().epochSecond
    return races
        .filter { it.raceEndTime > now }       // future race
        .minByOrNull { it.raceStartTime }      // soonest upcoming
}

fun getNextUpcomingSession(sessions: List<Session>): Session? {
    val now = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Instant.now().epochSecond
    } else {
        TODO("VERSION.SDK_INT < O")
    }
    return sessions
        .filter { it.startTime > now }
        .minByOrNull { it.startTime }
}

fun formatTimeLocal(epochSeconds: Long): String {
    val instant = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Instant.ofEpochSecond(epochSeconds)
    } else {
        TODO("VERSION.SDK_INT < O")
    }
    val formatter = DateTimeFormatter.ofPattern("hh:mm a")
        .withZone(ZoneId.systemDefault())
    return formatter.format(instant)
}

fun formatDateLocal(epochSeconds: Long): String {
    val instant = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Instant.ofEpochSecond(epochSeconds)
    } else {
        TODO("VERSION.SDK_INT < O")
    }
    val formatter = DateTimeFormatter.ofPattern("dd EEEE")
        .withZone(ZoneId.systemDefault())
    return formatter.format(instant)
}
