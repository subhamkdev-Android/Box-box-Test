package com.subhamkumar.boxboxapp.data.repository

import android.util.Log
import com.subhamkumar.boxboxapp.data.model.Race
import com.subhamkumar.boxboxapp.data.model.RaceResponse
import com.subhamkumar.boxboxapp.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class F1RepositoryImpl(
    private val apiService: ApiService
) : F1Repository {

    companion object {
        private const val TAG = "F1RepositoryImpl"

        private fun toMillisIfNeeded(epoch: Long): Long {
            return if (epoch in 0 until 1_000_000_000_000L) epoch * 1000L else epoch
        }

        private fun normalizeRaceTimes(race: Race): Race {
            val normalizedSessions = race.sessions.map { session ->
                session.copy(
                    startTime = toMillisIfNeeded(session.startTime),
                    endTime = toMillisIfNeeded(session.endTime)
                )
            }

            return race.copy(
                raceStartTime = toMillisIfNeeded(race.raceStartTime),
                raceEndTime = toMillisIfNeeded(race.raceEndTime),
                sessions = normalizedSessions
            )
        }
    }

    override suspend fun getTopDriver() = withContext(Dispatchers.IO) {
        try {
            apiService.getTopDriver()
        } catch (e: Exception) {
            Log.e(TAG, "Error fetching top driver", e)
            // return empty safe response
            com.subhamkumar.boxboxapp.data.model.DriverResponse(emptyList())
        }
    }

    override suspend fun getRaceDetails(): RaceResponse = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getRaceDetails() // original response

            val currentTime = System.currentTimeMillis()

            val normalizedSchedule = response.schedule.map { normalizeRaceTimes(it) }

            val upcomingRace = normalizedSchedule.minByOrNull { race ->
                race.sessions
                    .map { it.startTime }
                    .filter { it > currentTime }
                    .minOrNull() ?: Long.MAX_VALUE
            }

            val resultRace = upcomingRace?.let { it }

            RaceResponse(
                schedule = resultRace?.let { listOf(it) } ?: emptyList()
            )
        } catch (e: Exception) {
            Log.e(TAG, "Failed fetching/parsing race details", e)
            RaceResponse(emptyList())
        }
    }
}
