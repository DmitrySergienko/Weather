package ru.ds.weather.model.db

import android.app.Application
import android.os.Handler
import androidx.room.Room
import androidx.room.RoomDatabase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {

        private var appInstance: App? = null
        private var db: HistoryDataBase? = null
        private const val DB_NAME = "History.db"

        fun getHistoryDao(): HistoryDao {



                if (db == null) {
                    synchronized(HistoryDataBase::class.java) {
                        if (db == null) {
                            if (appInstance == null) throw IllegalStateException("Application is null while creating DataBase")



                                db = Room.databaseBuilder(appInstance!!.applicationContext, HistoryDataBase::class.java, DB_NAME)
                                        .allowMainThreadQueries()
                                        .fallbackToDestructiveMigration()//позволяет не настраивать миграцию при изменении бд будет пересоздаваться
                                        .build()

                        }

                    }
                }



            return db!!.historyDao()

        }
    }
}
