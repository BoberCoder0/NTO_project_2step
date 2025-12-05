package ru.myitschool.work.ui.screen.main

import ru.myitschool.work.data.dto.Booking

sealed class ProfileState {
    object Loading : ProfileState()
    data class Success(
        val name: String,
        val photo: String,
        val bookings: Map<String, Booking>
    ) : ProfileState()
    data class Error(val message: String) : ProfileState()
}