package com.homework.nasapicture.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.homework.nasapicture.model.EarthDTO
import com.homework.nasapicture.model.Retrofit
import com.homework.nasapicture.utils.API_KEY_ERROR
import com.homework.nasapicture.utils.SERVER_ERROR
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EarthViewModel(
    private val liveData: MutableLiveData<EarthState> = MutableLiveData(),
    private val earthPhotos: Retrofit = Retrofit()
) : ViewModel() {

    fun getLiveData(): LiveData<EarthState> {
        return liveData
    }

    fun sendRequest(date: String) {
        liveData.postValue(EarthState.Loading)
        if (com.homework.nasapicture.BuildConfig.NASA_API_KEY.isNullOrBlank()) {
            liveData.postValue(EarthState.Error(Throwable(API_KEY_ERROR)))
        } else {
            earthPhotos.getRetrofit()
                .getEarthImages(date, com.homework.nasapicture.BuildConfig.NASA_API_KEY)
                .enqueue(callback)
        }
    }

    private val callback = object : Callback<EarthDTO> {
        override fun onResponse(call: Call<EarthDTO>, response: Response<EarthDTO>) {
            if (response.isSuccessful) {
                response.body()?.let {
                    liveData.postValue(EarthState.Success(it))
                }
            } else {
                liveData.postValue(EarthState.Error(Throwable(SERVER_ERROR)))
            }
        }

        override fun onFailure(call: Call<EarthDTO>, t: Throwable) {
            liveData.postValue(EarthState.Error(Throwable(SERVER_ERROR)))
        }
    }
}