package com.subhamkumar.boxboxapp.data.model

import com.google.gson.annotations.SerializedName

data class DriverResponse(
    @SerializedName("drivers") val drivers: List<Driver>
)

data class Driver(
    @SerializedName("driverId") val driverId: String,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("position") val position: Int,
    @SerializedName("teamName") val teamName: String,
    @SerializedName("points") val points: Int,
    @SerializedName("teamId") val teamId: String,
    @SerializedName("racingNumber") val racingNumber: Int,
    @SerializedName("driverCode") val driverCode: String,
    @SerializedName("podiums") val podiums: Int,
    @SerializedName("wins") val wins: Int,
    @SerializedName("poles") val poles: Int,
) {
    val fullName: String get() = "$firstName $lastName"
}
