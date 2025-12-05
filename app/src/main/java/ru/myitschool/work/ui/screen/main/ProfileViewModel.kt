package ru.myitschool.work.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.myitschool.work.data.repo.AuthRepository
import ru.myitschool.work.data.repo.UserRepository

class ProfileViewModel : ViewModel() {

    private val _state = MutableStateFlow<ProfileState>(ProfileState.Loading)
    val state = _state.asStateFlow()

    init {
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            _state.value = ProfileState.Loading

            val code = AuthRepository.codeCache ?: run {
                _state.value = ProfileState.Error("Код потерялся")
                return@launch
            }

            val result = UserRepository.getUserInfo(code)

            if (result.isSuccess) {
                val userInfo = result.getOrNull()!!
                _state.value = ProfileState.Success(
                    name = userInfo.name,
                    photo = userInfo.photoUrl,
                    bookings = userInfo.booking
                )
            } else {
                _state.value = ProfileState.Error("Не получилось загрузить данные")
            }
        }
    }
    // 
    fun retry() {
        loadProfile()
    }
}