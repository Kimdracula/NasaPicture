package com.homework.nasapicture.viewmodel

import AsteroidsDTO
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.homework.nasapicture.model.Retrofit
import com.homework.nasapicture.utils.API_KEY_ERROR
import com.homework.nasapicture.utils.Date
import com.homework.nasapicture.utils.SERVER_ERROR
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AsteroidsViewModel(
    private val liveData: MutableLiveData<AsteroidsState> = MutableLiveData(),
    private val asteroids: Retrofit = Retrofit()
) : ViewModel() {

    fun getLiveData(): LiveData<AsteroidsState> {
        return liveData
    }

    fun sendRequest() {
        liveData.postValue(AsteroidsState.Loading)
        if (com.homework.nasapicture.BuildConfig.NASA_API_KEY.isNullOrBlank()) {
            liveData.postValue(AsteroidsState.Error(Throwable(API_KEY_ERROR)))
        } else {
            val myDate = Date()

            asteroids.getRetrofit().getAsteroids(
                myDate.formattedDby,
                myDate.formattedNow,
                com.homework.nasapicture.BuildConfig.NASA_API_KEY
            )
                .enqueue(callback)
        }
    }

    private val callback = object : Callback<AsteroidsDTO> {
        override fun onResponse(call: Call<AsteroidsDTO>, response: Response<AsteroidsDTO>) {
            if (response.isSuccessful) {
                response.body()?.let {
                    liveData.postValue(AsteroidsState.Success(it))
                }
            } else {
                liveData.postValue(AsteroidsState.Error(Throwable(SERVER_ERROR)))
            }
        }

        override fun onFailure(call: Call<AsteroidsDTO>, t: Throwable) {
            liveData.postValue(AsteroidsState.Error(Throwable(SERVER_ERROR)))
        }
    }
}