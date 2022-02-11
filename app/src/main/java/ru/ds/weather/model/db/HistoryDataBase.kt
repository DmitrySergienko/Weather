package ru.ds.weather.model.db

import androidx.room.Database
import androidx.room.RoomDatabase

//объект базы данных

@Database(entities = arrayOf(HistoryEntity::class), version = 1, exportSchema = false)
abstract class HistoryDataBase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao
}