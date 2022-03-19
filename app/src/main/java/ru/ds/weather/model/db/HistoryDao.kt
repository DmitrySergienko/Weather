package ru.ds.weather.model.db

import android.database.Cursor
import androidx.room.*

// объект доступа к данным бд
//Именно через DAO мы вносим данные в базу, удаляем или изменяем их
@Dao
interface HistoryDao {
    @Query("SELECT * FROM HistoryEntity")
    fun all(): List<HistoryEntity>

    @Query("SELECT * FROM HistoryEntity WHERE city LIKE :city")
    fun getDataByWord(city: String): List<HistoryEntity>

    @Query("UPDATE HistoryEntity SET 'temperature' = :temperature WHERE id = :id")
    fun updateQuery(temperature: Int, id: Long)

    @Query("DELETE FROM HistoryEntity WHERE city LIKE :city")
    fun deleteQuery(city: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: HistoryEntity)

    @Update
    fun update(entity: HistoryEntity)

    @Delete
    fun delete(entity: HistoryEntity)

    //--------------------------- ForContent Provider
    //Мы используем эти методы, чтобы получать и изменять данные БД из другого приложени

    @Query("DELETE FROM HistoryEntity WHERE id = :id")
    fun deleteById(id: Long)

    @Query("SELECT id, city, temperature FROM HistoryEntity")
    fun getHistoryCursor(): Cursor

    @Query("SELECT id, city, temperature FROM HistoryEntity WHERE id = :id")
    fun getHistoryCursor(id: Long): Cursor

}