package ru.myitschool.work.ui.screen.auth

sealed interface AuthState {
    object Loading: AuthState
    data class Data(
        val err: String = "",
        val code: String = ""
    ): AuthState
}