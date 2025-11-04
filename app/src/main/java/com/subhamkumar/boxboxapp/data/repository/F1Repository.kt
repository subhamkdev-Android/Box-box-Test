package com.subhamkumar.boxboxapp.data.repository

import com.subhamkumar.boxboxapp.data.model.DriverResponse
import com.subhamkumar.boxboxapp.data.model.RaceResponse
import retrofit2.Response

interface F1Repository {
    suspend fun getTopDriver(): DriverResponse
    suspend fun getRaceDetails(): RaceResponse
}