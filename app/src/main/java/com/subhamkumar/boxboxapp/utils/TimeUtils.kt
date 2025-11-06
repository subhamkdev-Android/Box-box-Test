package com.subhamkumar.boxboxapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.subhamkumar.boxboxapp.data.model.Race
import com.subhamkumar.boxboxapp.data.model.Session
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
fun getNextUpcomingRace(races: List<Race>): Race? {
    val now = Instant.now().epochSecond
    return races
        .filter { it.raceEndTime > now }       // future race (raceEndTime is epoch seconds)
        .minByOrNull { it.raceStartTime }      // soonest upcoming
}

@RequiresApi(Build.VERSION_CODES.O)
fun getNextUpcomingSession(sessions: List<Session>): Session? {
    val now = Instant.now().epochSecond
    return sessions
        .filter { it.startTime > now }      // sessions' startTime expected in epoch seconds
        .minByOrNull { it.startTime }
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatTimeLocal(epochSeconds: Long): String {
    val instant = Instant.ofEpochSecond(epochSeconds)
    val formatter = DateTimeFormatter.ofPattern("h:mm", Locale.ENGLISH)
        .withZone(ZoneId.systemDefault())
    return formatter.format(instant)
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatAmPm(epochSeconds: Long): String {
    val instant = Instant.ofEpochSecond(epochSeconds)
    val formatter = DateTimeFormatter.ofPattern("a", Locale.ENGLISH)
        .withZone(ZoneId.systemDefault())
    return formatter.format(instant).uppercase(Locale.ENGLISH)
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatTimeAndAmPm(epochSeconds: Long): Pair<String, String> {
    val instant = Instant.ofEpochSecond(epochSeconds)
    val formatter = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH)
        .withZone(ZoneId.systemDefault())
    val formatted = formatter.format(instant) // e.g. "8:00 AM"
    val parts = formatted.split(" ")
    val time = parts.getOrNull(0) ?: ""
    val amPm = parts.getOrNull(1)?.uppercase(Locale.ENGLISH) ?: ""
    return Pair(time, amPm)
}


@RequiresApi(Build.VERSION_CODES.O)
fun formatDateLocal(epochSeconds: Long): String {
    val instant = Instant.ofEpochSecond(epochSeconds)
    val formatter = DateTimeFormatter.ofPattern("dd EEEE", Locale.ENGLISH)
        .withZone(ZoneId.systemDefault())
    return formatter.format(instant)
}
