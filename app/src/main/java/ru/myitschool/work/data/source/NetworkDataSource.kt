package ru.myitschool.work.data.source

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import ru.myitschool.work.core.Constants
import ru.myitschool.work.data.dto.BookRequest
import ru.myitschool.work.data.dto.BookingResponse
import ru.myitschool.work.data.dto.UserInfoResponse

object NetworkDataSource {
    private val client by lazy {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(
                    Json {
                        isLenient = true
                        ignoreUnknownKeys = true
                        explicitNulls = true
                        encodeDefaults = true
                    }
                )
            }
            expectSuccess = false
        }
    }

    suspend fun checkAuth(code: String): Result<Boolean> = withContext(Dispatchers.IO) {
        return@withContext runCatching {
            val response = client.get(getUrl(code, Constants.AUTH_URL))
            when (response.status) {
                HttpStatusCode.OK -> true
                else -> error(response.bodyAsText())
            }
        }
    }

    // Без runCatching т.к. надо различать ошибки
    suspend fun getUserInfo(code: String): Result<UserInfoResponse> = withContext(Dispatchers.IO) {
        try {
            val response = client.get(getUrl(code, Constants.INFO_URL)) {
            }
            when (response.status) {
                HttpStatusCode.OK -> {
                    val userInfo = response.body<UserInfoResponse>()
                    Result.success(userInfo)
                }
                HttpStatusCode.BadRequest -> {
                    Result.failure(Exception("что-то пошло не так"))
                }
                HttpStatusCode.Unauthorized -> {
                    Result.failure(Exception("кода не существует"))
                }
                else -> {
                    Result.failure(Exception("Неизвестная ошибка: ${response.status}"))
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getBooking(code: String): Result<BookingResponse> = withContext(Dispatchers.IO) {
        try {
            val response = client.get(getUrl(code, Constants.BOOKING_URL)) {
            }
            when (response.status) {
                HttpStatusCode.OK -> {
                    val userInfo = response.body<BookingResponse>()
                    Result.success(userInfo)
                }
                HttpStatusCode.BadRequest -> {
                    Result.failure(Exception("что-то пошло не так"))
                }
                HttpStatusCode.Unauthorized -> {
                    Result.failure(Exception("кода не существует"))
                }
                else -> {
                    Result.failure(Exception("Неизвестная ошибка: ${response.status}"))
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun createBook(code: String, date: String, placeId: Int): Result<Boolean> = withContext(Dispatchers.IO) {
        try {
            val response = client.post(getUrl(code, Constants.BOOK_URL)) {
                setBody(BookRequest(date = date, placeId = placeId))
                contentType(ContentType.Application.Json)
            }

            when (response.status) {
                HttpStatusCode.Created -> {
                    Result.success(true)
                }
                HttpStatusCode.BadRequest -> {
                    Result.failure(Exception("Неверные данные"))
                }
                HttpStatusCode.Unauthorized -> {
                    Result.failure(Exception("Код недействителен"))
                }
                HttpStatusCode.Conflict -> {
                    Result.failure(Exception("Это место уже занято"))
                }
                else -> {
                    Result.failure(Exception("Ошибка: ${response.status}"))
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun getUrl(code: String, targetUrl: String) = "${Constants.HOST}/api/$code$targetUrl"
}