package ru.ds.weather.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

const val ID = "id"
const val CITY = "city"
const val TEMPERATURE = "temperature"
//сущность или таблица для бд
@Entity
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val city: String = "",
    val temperature: Int =0,
    val condition: String = ""


)
