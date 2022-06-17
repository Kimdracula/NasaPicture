package com.homework.nasapicture.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.homework.nasapicture.model.PictureOfTheDayDTO
import com.homework.nasapicture.model.Retrofit
import com.homework.nasapicture.utils.API_KEY_ERROR
import com.homework.nasapicture.utils.SERVER_ERROR
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(
    private val liveData: MutableLiveData<POTDState> = MutableLiveData(),
    private val pictureOfTheDayRetrofit: Retrofit = Retrofit()
) : ViewModel() {

    fun getLiveData(): LiveData<POTDState> {
        return liveData
    }

    fun sendRequest(date: String) {
        liveData.postValue(POTDState.Loading)
        if (com.homework.nasapicture.BuildConfig.NASA_API_KEY.isNullOrBlank()) {
            liveData.postValue(POTDState.Error(Throwable(API_KEY_ERROR)))
        } else {
            pictureOfTheDayRetrofit.getRetrofit()
                .getAPOD(com.homework.nasapicture.BuildConfig.NASA_API_KEY, date)
                .enqueue(callback)
        }
    }

    private val callback = object : Callback<PictureOfTheDayDTO> {
        override fun onResponse(
            call: Call<PictureOfTheDayDTO>,
            response: Response<PictureOfTheDayDTO>
        ) {
            if (response.isSuccessful) {
                response.body()?.let {
                    liveData.postValue(POTDState.Success(it))
                }
            } else {
                liveData.postValue(POTDState.Error(Throwable(SERVER_ERROR)))
            }
        }

        override fun onFailure(call: Call<PictureOfTheDayDTO>, t: Throwable) {
            liveData.postValue(POTDState.Error(Throwable(SERVER_ERROR)))
        }
    }
}