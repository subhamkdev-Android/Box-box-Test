package com.subhamkumar.boxboxapp.data.repository

import com.subhamkumar.boxboxapp.data.model.Driver
import com.subhamkumar.boxboxapp.data.model.DriverResponse
import com.subhamkumar.boxboxapp.data.model.Race
import com.subhamkumar.boxboxapp.data.model.RaceResponse
import com.subhamkumar.boxboxapp.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class F1RepositoryImpl(
    private val apiService: ApiService
) : F1Repository {

    override suspend fun getTopDriver(): DriverResponse = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getTopDriver()
            val topDriver = response.drivers.firstOrNull { it.position == 1 }
            DriverResponse(
                drivers = topDriver?.let { listOf(it) } ?: emptyList()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            DriverResponse(emptyList())
        }
    }

    override suspend fun getRaceDetails(): RaceResponse = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getRaceDetails()
            val currentTime = System.currentTimeMillis()

            // Next upcoming race
            val upcomingRace = response.schedule.minByOrNull { race ->
                race.sessions
                    .filter { it.startTime > currentTime }
                    .minOfOrNull { it.startTime } ?: Long.MAX_VALUE
            }

            RaceResponse(
                schedule = upcomingRace?.let { listOf(it) } ?: emptyList()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            RaceResponse(emptyList())
        }
    }
}
