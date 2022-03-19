package ru.ds.weather.repository


import ru.ds.weather.model.Weather



interface LocalRepository {
    fun getAllHistory(): List<Weather>
    fun saveEntity(weather: Weather)
}