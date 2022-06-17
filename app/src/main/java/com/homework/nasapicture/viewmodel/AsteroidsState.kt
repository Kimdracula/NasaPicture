package com.homework.nasapicture.viewmodel

import AsteroidsDTO


 sealed class AsteroidsState {

        object Loading : AsteroidsState()
        data class Success(val asteroids: AsteroidsDTO) : AsteroidsState()
        data class Error(val error: Throwable) : AsteroidsState()
 }
