package ru.myitschool.work.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Booking(
    @SerialName("id")
    val id: Int,
    @SerialName("place")
    val place: String
)
