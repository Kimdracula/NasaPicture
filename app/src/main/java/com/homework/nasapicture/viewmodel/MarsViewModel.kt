package com.homework.nasapicture.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.homework.nasapicture.model.MarsRoverPhotosDTO
import com.homework.nasapicture.model.Retrofit
import com.homework.nasapicture.utils.API_KEY_ERROR
import com.homework.nasapicture.utils.SERVER_ERROR
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarsViewModel (

    private val liveData: MutableLiveData<MarsState> = MutableLiveData(),
    private val marsRoverPhotos: Retrofit = Retrofit()
    ) : ViewModel() {

        fun getLiveData(): LiveData<MarsState> {
            return liveData
        }

        fun sendRequest(date: String) {
            liveData.postValue(MarsState.Loading)
            if (com.homework.nasapicture.BuildConfig.NASA_API_KEY.isNullOrBlank()) {
                liveData.postValue(MarsState.Error(Throwable(API_KEY_ERROR)))
            } else {
                marsRoverPhotos.getRetrofit().getMarsRoverPhotos(date,com.homework.nasapicture.BuildConfig.NASA_API_KEY)
                    .enqueue(callback)
            }
        }

        private val callback = object : Callback<MarsRoverPhotosDTO> {
            override fun onResponse(call: Call<MarsRoverPhotosDTO>, response: Response<MarsRoverPhotosDTO>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        liveData.postValue(MarsState.Success(it))
                    }
                } else {
                    liveData.postValue(MarsState.Error(Throwable(SERVER_ERROR)))
                }
            }

            override fun onFailure(call: Call<MarsRoverPhotosDTO>, t: Throwable) {
                liveData.postValue(MarsState.Error(Throwable(SERVER_ERROR)))
            }
        }
    }