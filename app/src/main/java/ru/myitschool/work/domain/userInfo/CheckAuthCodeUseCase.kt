package ru.myitschool.work.domain.userInfo

import ru.myitschool.work.data.dto.UserInfoResponse
import ru.myitschool.work.data.repo.UserRepository

class CheckUserCodeUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(code: String): Result<UserInfoResponse> = repository.getUserInfo(code)
}