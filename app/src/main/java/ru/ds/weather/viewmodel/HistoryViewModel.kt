package ru.ds.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.ds.weather.model.AppState
import ru.ds.weather.model.db.App.Companion.getHistoryDao
import ru.ds.weather.repository.LocalRepository
import ru.ds.weather.repository.LocalRepositoryImpl


//класс для отображения данных
class HistoryViewModel(
    val historyLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val historyRepository: LocalRepository = LocalRepositoryImpl(getHistoryDao())
) : ViewModel() {
//метод для получения данных из базы
    fun getAllHistory() {
        historyLiveData.value = AppState.Loading
        historyLiveData.value = AppState.Success(historyRepository.getAllHistory())
    }
}