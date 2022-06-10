package com.homework.nasapicture.model

import com.homework.nasapicture.utils.API_HEADER
import com.homework.nasapicture.utils.END_POINT_APOD
import com.homework.nasapicture.utils.END_POINT_MRP
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureApi {
    @GET(END_POINT_APOD) //Picture of the day
    fun getAPOD(@Query(API_HEADER) apikey: String, @Query("date") date:String): Call<PictureOfTheDayDTO>

    @GET(END_POINT_MRP) // Mars Rover Photos
fun getMarsRoverPhotos(@Query("earth_date") date:String,@Query(API_HEADER) apikey: String ):  Call<MarsRoverPhotosDTO>
}