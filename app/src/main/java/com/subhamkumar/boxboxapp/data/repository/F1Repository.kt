package com.subhamkumar.boxboxapp.data.repository

import com.subhamkumar.boxboxapp.data.network.RetrofitInstance
import com.subhamkumar.boxboxapp.data.model.DriverResponse
import com.subhamkumar.boxboxapp.data.model.RaceResponse
import retrofit2.Response

class F1Repository {

    suspend fun getTopDrivers(): Response<DriverResponse> {
        return RetrofitInstance.api.getDrivers()
    }

    suspend fun getUpcomingRace(): Response<RaceResponse> {
        return RetrofitInstance.api.getRaceDetails()
    }
}