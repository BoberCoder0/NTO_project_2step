package ru.myitschool.work.data.repo

import ru.myitschool.work.data.dto.UserInfoResponse
import ru.myitschool.work.data.source.NetworkDataSource

object CreateBookRepository {
    suspend fun CreateBook(code: String, date: String, placeId: Int): Result<Boolean> {
        return NetworkDataSource.createBook(code, date, placeId).onSuccess { success ->
            print("✔✔✔ Работает")
        }
    }
}