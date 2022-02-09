package ru.ds.weather.repository

import okhttp3.Callback

//Этот интерфейс обозначает работу с данными на экране DetailsFragment

interface DetailsRepository {
    fun getWeatherDetailsFromServer(requestLink: String, callback: Callback)
}