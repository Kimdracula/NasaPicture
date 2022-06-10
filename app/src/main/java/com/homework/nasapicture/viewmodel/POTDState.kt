package com.homework.nasapicture.viewmodel

import com.homework.nasapicture.model.PictureOfTheDayDTO

sealed class POTDState {
    object Loading : POTDState()
    data class Success(val pictureOfTheDay: PictureOfTheDayDTO) : POTDState()
    data class Error(val error: Throwable) : POTDState()

}