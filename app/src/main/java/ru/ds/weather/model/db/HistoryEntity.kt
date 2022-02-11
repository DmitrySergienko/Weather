package ru.ds.weather.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey


//сущность или таблица для бд
@Entity
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val city: String,
    val temperature: Int,
    val condition: String


)
