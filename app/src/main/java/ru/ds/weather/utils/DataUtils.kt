package ru.ds.weather.utils

import ru.ds.weather.model.*
import ru.ds.weather.model.db.HistoryEntity

//Метод convertDtoToModel занимается преобразованием нашего Data transfer object в понятный для AppState формат:
fun convertDtoToModel(weatherDTO: WeatherDTO): List<Weather> {
    val fact: FactDTO = weatherDTO.fact!!
    return listOf(Weather(getDefaultCity(), fact.temp!!, fact.feels_like!!, fact.condition!!, fact.icon))
}
//метод для конвертации историии
fun convertHistoryEntityToWeather(entityList: List<HistoryEntity>): List<Weather> {
    return entityList.map {
        Weather(City(it.city, 0.0, 0.0), it.temperature, 0, it.condition)
    }
}
//метод для конвертации погодя в Entity (таблицу)
fun convertWeatherToEntity(weather: Weather): HistoryEntity = HistoryEntity(0, weather.city.city, weather.temperature, weather.condition)
