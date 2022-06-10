package com.homework.nasapicture.model

import com.google.gson.GsonBuilder
import com.homework.nasapicture.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {
    fun getRetrofit(): NasaApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
        return retrofit.create(NasaApi::class.java)
    }
}

