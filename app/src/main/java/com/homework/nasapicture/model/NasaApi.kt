package com.homework.nasapicture.model

import AsteroidsDTO
import com.homework.nasapicture.utils.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {
    @GET(END_POINT_APOD) //Picture of the day
    fun getAPOD(
        @Query(API_KEY) apikey: String,
        @Query("date") date: String
    ): Call<PictureOfTheDayDTO>

    @GET(END_POINT_MRP) // Mars Rover Photos
    fun getMarsRoverPhotos(
        @Query("earth_date") date: String,
        @Query("name") name: String,
        @Query(API_KEY) apikey: String
    ): Call<MarsRoverPhotosDTO>

    @GET(END_POINT_ASTEROIDS) // Asteroids Info
    fun getAsteroids(
        @Query("start_date") start_date: String,
        @Query("end_date") end_date: String,
        @Query(API_KEY) apikey: String
    ): Call<AsteroidsDTO>

    @GET(END_POINT_EARTH_CODE) // Earth photos
    fun getEarthImages(
        @Query("date") date: String,
        @Query(API_KEY) apikey: String
    ): Call<EarthDTO>

}