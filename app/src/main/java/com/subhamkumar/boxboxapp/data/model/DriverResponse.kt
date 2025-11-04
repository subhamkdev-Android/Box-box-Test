package com.subhamkumar.boxboxapp.data.model

import com.google.gson.annotations.SerializedName

data class DriverResponse(
    @SerializedName("drivers") val drivers: List<Driver>
)

data class Driver(
    @SerializedName("position") val position: Int,
    @SerializedName("driverName") val driverName: String,
    @SerializedName("team") val team: String,
    @SerializedName("points") val points: String,
    @SerializedName("nationality") val nationality: String,
    @SerializedName("imageUrl") val imageUrl: String
)
