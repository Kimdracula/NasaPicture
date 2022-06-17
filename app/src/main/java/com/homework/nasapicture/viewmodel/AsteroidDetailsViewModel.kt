package com.homework.nasapicture.viewmodel

import AsteroidsDTO
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.homework.nasapicture.utils.SERVER_ERROR


class AsteroidDetailsViewModel(
    private val liveData: MutableLiveData<AsteroidsDetailsState> = MutableLiveData(),
) : ViewModel() {


    fun getLiveData(): LiveData<AsteroidsDetailsState> {
        return liveData
    }

    fun getAsteroidsDetails(asteroidsList: AsteroidsDTO) {
        liveData.postValue(AsteroidsDetailsState.Loading)
        if (asteroidsList == null) {
            liveData.postValue(AsteroidsDetailsState.Error(Throwable(SERVER_ERROR)))
        } else liveData.postValue(AsteroidsDetailsState.Success(asteroidsList))
    }
}