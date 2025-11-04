package com.subhamkumar.boxboxapp.data.model


import com.google.gson.annotations.SerializedName

data class RaceResponse(
    @SerializedName("races") val races: List<Race>
)

data class Race(
    @SerializedName("round") val round: String,
    @SerializedName("raceName") val raceName: String,
    @SerializedName("circuitId") val circuitId: String,
    @SerializedName("raceStartTime") val raceStartTime: String,
    @SerializedName("raceEndTime") val raceEndTime: String,
    @SerializedName("sessions") val sessions: List<Session>
)

data class Session(
    @SerializedName("sessionName") val sessionName: String,
    @SerializedName("startTime") val startTime: String,
    @SerializedName("endTime") val endTime: String
)

