package com.homework.nasapicture.viewmodel

import com.homework.nasapicture.model.MarsRoverPhotosDTO

sealed class MarsState{
object Loading : MarsState()
data class Success(val marsRoverPhotos: MarsRoverPhotosDTO) : MarsState()
data class Error(val error: Throwable) : MarsState()}
