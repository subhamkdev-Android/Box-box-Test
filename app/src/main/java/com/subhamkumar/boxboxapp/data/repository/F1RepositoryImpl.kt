package com.subhamkumar.boxboxapp.data.repository

import com.subhamkumar.boxboxapp.data.model.DriverResponse
import com.subhamkumar.boxboxapp.data.model.RaceResponse
import com.subhamkumar.boxboxapp.data.network.ApiService

class F1RepositoryImpl(
    private val apiService: ApiService
) : F1Repository {

    override suspend fun getTopDriver(): DriverResponse {
        return try {
            val response = apiService.getTopDriver()
            DriverResponse(
                drivers = response.drivers ?: emptyList()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            DriverResponse(emptyList())
        }
    }

    override suspend fun getRaceDetails(): RaceResponse {
        return try {
            val response = apiService.getRaceDetails()
            RaceResponse(
                schedule = response.schedule ?: emptyList()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            RaceResponse(emptyList())
        }
    }
}
