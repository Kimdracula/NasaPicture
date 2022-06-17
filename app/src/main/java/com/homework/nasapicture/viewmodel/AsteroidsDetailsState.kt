package com.homework.nasapicture.viewmodel

import AsteroidsDTO

sealed class AsteroidsDetailsState {

    object Loading : AsteroidsDetailsState()
    data class Success(val asteroids: AsteroidsDTO) : AsteroidsDetailsState()
    data class Error(val error: Throwable) : AsteroidsDetailsState()
}