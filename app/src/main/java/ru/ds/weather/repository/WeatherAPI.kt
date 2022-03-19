package ru.ds.weather.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.ds.weather.model.WeatherDTO

interface WeatherAPI {
    @GET("v2/informers")
    fun getWeather(
       // @Header("X-Yandex-API-Key") token: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Call<WeatherDTO>
}