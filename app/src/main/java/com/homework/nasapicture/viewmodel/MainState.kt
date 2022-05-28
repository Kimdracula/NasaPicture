package com.homework.nasapicture.viewmodel

import com.homework.nasapicture.model.NasaDTO

sealed class MainState {
    object Loading : MainState()
    data class Success(val pictureOfTheDay: NasaDTO) : MainState()
    data class Error(val error: Throwable) : MainState()
}