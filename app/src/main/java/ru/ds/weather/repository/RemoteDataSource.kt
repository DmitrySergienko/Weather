package ru.ds.weather.repository

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.ds.weather.BuildConfig
import ru.ds.weather.model.WeatherDTO
import java.io.IOException

//"Это класс где происходит запрос на сервер

class RemoteDataSource {
    private val weatherApi = Retrofit.Builder()
        .baseUrl("https://api.weather.yandex.ru/")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .client(createOkHttpClient(WeatherApiInterceptor())) // interceptor
        .build()
        .create(WeatherAPI::class.java)

    fun getWeatherDetails(lat: Double, lon: Double, callback: Callback<WeatherDTO>) {
        weatherApi.getWeather(lat, lon).enqueue(callback)
    }
    //----------------------------------------------------
//interceptor implementation
    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        httpClient.addInterceptor(KeyInterceptor())
        return httpClient.build()
    }

    inner class WeatherApiInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            return chain.proceed(chain.request())
        }
    }
    //----------------------------------------------------
//для того что-бы не передвать ключь в запрос
    inner class KeyInterceptor : Interceptor{
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
                .newBuilder()
                .addHeader("X-Yandex-API-Key",BuildConfig.WEATHER_API_KEY)
                .build()
            return chain.proceed(request)
        }
    }
}