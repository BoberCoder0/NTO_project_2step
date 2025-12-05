package ru.myitschool.work.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class BookingResponse(
    val booking: Map<String, List<Booking>>
)