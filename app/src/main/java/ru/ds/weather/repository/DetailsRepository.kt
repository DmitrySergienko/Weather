package ru.ds.weather.repository


import ru.ds.weather.model.WeatherDTO

//Этот интерфейс обозначает работу с данными на экране DetailsFragment

interface DetailsRepository {
    fun getWeatherDetailsFromServer(
        lat: Double,
        lon: Double,
        callback: retrofit2.Callback<WeatherDTO>
    )

}