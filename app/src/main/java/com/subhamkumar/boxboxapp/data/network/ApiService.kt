package com.subhamkumar.boxboxapp.data.network

import com.subhamkumar.boxboxapp.data.model.DriverResponse
import com.subhamkumar.boxboxapp.data.model.RaceResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    // Fetch top driver
    @GET("e8616da8-220c-4aab-a670-ab2d43224ecb")
    suspend fun getDrivers(): Response<DriverResponse>

    // Fetch race details
    @GET("9086a3f1-f02b-4d24-8dd3-b63582f45e67")
    suspend fun getRaceDetails(): Response<RaceResponse>
}