package ru.ds.weather.model.db

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

}