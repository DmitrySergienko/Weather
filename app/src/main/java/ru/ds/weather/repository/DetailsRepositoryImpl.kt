package ru.ds.weather.repository

import ru.ds.weather.model.WeatherDTO


//В репозиторий мы передаём источник данных — так репозиторий получает данные извне.
class DetailsRepositoryImpl(private val remoteDataSource: RemoteDataSource) :
    DetailsRepository {

    override fun getWeatherDetailsFromServer(
        lat: Double,
        lon: Double,
        callback: retrofit2.Callback<WeatherDTO>
    ) {
        remoteDataSource.getWeatherDetails(lat, lon, callback)
    }
}