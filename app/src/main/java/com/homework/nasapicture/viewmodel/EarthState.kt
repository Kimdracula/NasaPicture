package com.homework.nasapicture.viewmodel

import com.homework.nasapicture.model.EarthDTO


sealed class EarthState{
    object Loading : EarthState()
    data class Success(val earthPhotos: EarthDTO) : EarthState()
    data class Error(val error: Throwable) : EarthState()}