package ru.ds.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.ds.weather.viewmodel.AppState
import ru.ds.weather.model.Repository
import ru.ds.weather.model.RepositoryImpl
import java.lang.Thread.sleep


class MainViewModel(
        private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
        private val repositoryImpl: Repository = RepositoryImpl()
) :
        ViewModel() {

    fun getLiveData() = liveDataToObserve         //метод для получения LiveData

        // fun getWeatherFromLocalSource() = getDataFromLocalSource()  //метод для получения данных
            // fun getWeatherFromRemoteSource() = getDataFromLocalSource() //метод для получения данных

    fun getWeather() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(1000)
            liveDataToObserve.postValue(AppState.Success(repositoryImpl.getWeatherFromLocalStorage()))
        }.start()
    }
}