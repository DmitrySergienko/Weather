package ru.ds.weather.repository

import ru.ds.weather.model.Weather
import ru.ds.weather.model.db.HistoryDao
import ru.ds.weather.utils.convertHistoryEntityToWeather
import ru.ds.weather.utils.convertWeatherToEntity

class LocalRepositoryImpl(private val localDataSource: HistoryDao) :
    LocalRepository {
//из юазы данных берем историю
    override fun getAllHistory(): List<Weather> {
        return convertHistoryEntityToWeather(localDataSource.all())
    }
// сохранение в таблице
    override fun saveEntity(weather: Weather) {
        localDataSource.insert(convertWeatherToEntity(weather))
    }
}