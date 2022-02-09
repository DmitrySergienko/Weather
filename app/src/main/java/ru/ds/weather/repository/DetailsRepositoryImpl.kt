package ru.ds.weather.repository

import okhttp3.Callback


//В репозиторий мы передаём источник данных — так репозиторий получает данные извне.
class DetailsRepositoryImpl(private val remoteDataSource: RemoteDataSource) :
    DetailsRepository {

    override fun getWeatherDetailsFromServer(requestLink: String, callback: Callback) {
        remoteDataSource.getWeatherDetails(requestLink, callback)
    }
}