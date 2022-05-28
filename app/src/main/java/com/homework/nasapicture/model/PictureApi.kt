package com.homework.nasapicture.model

import com.homework.nasapicture.utils.API_HEADER
import com.homework.nasapicture.utils.END_POINT
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface PictureApi {
    @GET(END_POINT)
    fun getNasaPicture(
        @Header(API_HEADER) apikey: String
    ): Call<NasaDTO>
}