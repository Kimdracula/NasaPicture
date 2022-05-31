package com.homework.nasapicture.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.homework.nasapicture.BuildConfig
import com.homework.nasapicture.model.NasaDTO
import com.homework.nasapicture.model.Retrofit
import com.homework.nasapicture.utils.API_KEY_ERROR
import com.homework.nasapicture.utils.SERVER_ERROR
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(
    private val liveData: MutableLiveData<MainState> = MutableLiveData(),
    private val pictureOfTheDayRetrofit: Retrofit = Retrofit()
) : ViewModel() {

    fun getLiveData(): LiveData<MainState> {
        return liveData
    }

    fun sendRequest(date: String) {
        liveData.postValue(MainState.Loading)
        if (BuildConfig.NASA_API_KEY.isNullOrBlank()) {
            liveData.postValue(MainState.Error(Throwable(API_KEY_ERROR)))
        } else {
            pictureOfTheDayRetrofit.getRetrofit().getNasaPicture(BuildConfig.NASA_API_KEY, date)
                .enqueue(callback)
        }
    }

    private val callback = object : Callback<NasaDTO> {
        override fun onResponse(call: Call<NasaDTO>, response: Response<NasaDTO>) {
            if (response.isSuccessful) {
                response.body()?.let {
                    liveData.postValue(MainState.Success(it))
                }
            } else {
                liveData.postValue(MainState.Error(Throwable(SERVER_ERROR)))
            }
        }

        override fun onFailure(call: Call<NasaDTO>, t: Throwable) {
            liveData.postValue(MainState.Error(Throwable(SERVER_ERROR)))
        }
    }
}