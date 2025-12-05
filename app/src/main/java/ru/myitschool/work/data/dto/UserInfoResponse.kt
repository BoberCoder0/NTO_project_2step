package ru.myitschool.work.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoResponse(
    @SerialName("name")
    val name: String,
    @SerialName("photoUrl")
    val photoUrl: String,
    @SerialName("booking")
    val booking: Map<String, Booking> = emptyMap() // ключ — дата
)