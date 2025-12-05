package ru.myitschool.work.data.repo

import ru.myitschool.work.data.dto.BookingResponse
import ru.myitschool.work.data.dto.UserInfoResponse
import ru.myitschool.work.data.source.NetworkDataSource

object BookingRepository {

    suspend fun getBooking(code: String): Result<BookingResponse> {
        return NetworkDataSource.getBooking(code).onSuccess { success ->
            print("✔✔✔ Работает")
        }
    }
}