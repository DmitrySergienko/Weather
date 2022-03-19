package ru.ds.weather.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize

data class Weather(
    val city: City = getDefaultCity(),
    val temperature: Int = 0,
    val feelsLike: Int = 0,
    val condition: String = "sunny",
    val icon: String? = "bkn_n"

) : Parcelable


fun getDefaultCity() = City("Москва", 55.755826, 37.617299900000035)

//Это методы, которые возвращают массивы городов: русских или зарубежных
fun getWorldCities() = listOf(
        Weather(City("London", 51.5085300, -0.1257400), 1, 2),
        Weather(City("Tokyo", 35.6895000, 139.6917100), 3, 4),
        Weather(City("Paris", 48.8534100, 2.3488000), 5, 6),
        Weather(City("Berlin", 52.52000659999999, 13.404953999999975), 7, 8),
        Weather(City("Rome", 41.9027835, 12.496365500000024), 9, 10),
        Weather(City("Minsk", 53.90453979999999, 27.561524400000053), 11, 12),
        Weather(City("Istanbul", 41.0082376, 28.97835889999999), 13, 14),
        Weather(City("Washington", 38.9071923, -77.03687070000001), 15, 16),
        Weather(City("Kyiv", 50.4501, 30.523400000000038), 17, 18),
        Weather(City("Beijing", 39.90419989999999, 116.40739630000007), 19, 20),
        Weather(City("Moscow", 55.755826, 37.617299900000035), 1, 2),
        Weather(City("St. Petersburg", 59.9342802, 30.335098600000038), 3, 3),
        Weather(City("Novosibirsk", 55.00835259999999, 82.93573270000002), 5, 6),
        Weather(City("Ekaterinburg", 56.83892609999999, 60.60570250000001), 7, 8),
        Weather(City("Nizhny Novgorod", 56.2965039, 43.936059), 9, 10),
        Weather(City("Kazan", 55.8304307, 49.06608060000008), 11, 12),
        Weather(City("Chelyabinsk", 55.1644419, 61.4368432), 13, 14),
        Weather(City("Omsk", 54.9884804, 73.32423610000001), 15, 16),
        Weather(City("Rostov-on-Don", 47.2357137, 39.701505), 17, 18),
        Weather(City("Ufa", 54.7387621, 55.972055400000045), 19, 20)
    )




