package ru.ds.weather.repository

import ru.ds.weather.model.Weather
//import ru.ds.weather.model.getRussianCities
import ru.ds.weather.model.getWorldCities

class RepositoryImpl : Repository {

    override fun getWeatherFromServer() = Weather()

            //override fun getWeatherFromLocalStorageRus() = getRussianCities()

    override fun getWeatherFromLocalStorageWorld() = getWorldCities()

}