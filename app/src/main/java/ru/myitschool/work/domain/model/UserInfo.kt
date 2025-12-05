package ru.myitschool.work.domain.model
import ru.myitschool.work.data.dto.Booking

data class UserInfo(
    val name: String,
    val photoUrl: String,
    val booking: Map<String, Booking> = emptyMap() // ключ — дата
)
