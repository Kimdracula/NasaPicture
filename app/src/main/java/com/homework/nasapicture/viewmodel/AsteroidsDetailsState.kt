package com.homework.nasapicture.viewmodel
import com.homework.nasapicture.model.X20150907

sealed class AsteroidsDetailsState {

    object Loading : AsteroidsDetailsState()
    data class Success(val asteroids: X20150907) : AsteroidsDetailsState()
    data class Error(val error: Throwable) : AsteroidsDetailsState()
}