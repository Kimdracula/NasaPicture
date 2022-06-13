package com.homework.nasapicture.model

import com.homework.nasapicture.utils.API_HEADER
import com.homework.nasapicture.utils.END_POINT_APOD
import com.homework.nasapicture.utils.END_POINT_ASTEROIDS
import com.homework.nasapicture.utils.END_POINT_MRP
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {
    @GET(END_POINT_APOD) //Picture of the day
    fun getAPOD(
        @Query(API_HEADER) apikey: String,
        @Query("date") date: String
    ): Call<PictureOfTheDayDTO>

    @GET(END_POINT_MRP) // Mars Rover Photos
    fun getMarsRoverPhotos(
        @Query("earth_date") date: String,
        @Query("name") name: String,
        @Query(API_HEADER) apikey: String
    ): Call<MarsRoverPhotosDTO>

    @GET(END_POINT_ASTEROIDS) // Asteroids Info
    fun getAsteroids(
        @Query("start_date") start_date: String,
        @Query("end_date") end_date: String,
        @Query(API_HEADER) apikey: String
    ): Call<AsteroidsDTO>

}