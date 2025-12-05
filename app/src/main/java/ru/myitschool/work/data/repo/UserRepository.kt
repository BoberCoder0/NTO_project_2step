package ru.myitschool.work.data.repo

import ru.myitschool.work.data.dto.UserInfoResponse
import ru.myitschool.work.data.source.NetworkDataSource

object UserRepository {

    suspend fun getUserInfo(text: String): Result<UserInfoResponse> {
        return NetworkDataSource.getUserInfo(text).onSuccess { success ->
            // Радоваться ¯\_(ツ)_/¯
        }
    }
}