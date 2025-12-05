package ru.myitschool.work.domain.model

import ru.myitschool.work.data.dto.Booking

data class GetBooking(
    val booking: Map<String, List<Booking>>
)
