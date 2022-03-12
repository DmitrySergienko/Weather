package ru.ds.weather.repository

import ru.ds.weather.model.Weather

interface Repository {
    fun getWeatherFromServer(): Weather
   // fun getWeatherFromLocalStorageRus(): List<Weather>
    fun getWeatherFromLocalStorageWorld(): List<Weather>
}

