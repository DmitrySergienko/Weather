package ru.ds.weather
/*
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.location.LocationServices

class GeoZones : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)

//Для создания геозоны надо создать клиента:
        val geofencingClient: GeofencingClient =
            LocationServices.getGeofencingClient(this)


//Создаём геозону, где указываем координаты и расстояние, на котором срабатывает триггер (точность):
        val geofence: Geofence = Geofence.Builder()
            .setRequestId("test")
            .setCircularRegion(51.5085300, -0.1257400, 15)
            .build()

        val geofencingRequest = GeofencingRequest.Builder()
            .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            .addGeofences(listOf(geofence)).build()
//Затем создаём интент, где указываем службу, обрабатывающую сигналы геозоны:
        val geoService = Intent(context, GeoFenceService::class.java)
        val pendingIntent = PendingIntent
            .getService(context, 0, geoService, PendingIntent.FLAG_UPDATE_CURRENT)


//Добавим геозоны клиенту GeofencingRequest:
        geoClient.addGeofences(geoFenceRequest, pendingIntent)
//Нужно помнить, что всё это работает через службы Google Play. Поэтому перед созданием клиента геозоны надо создать клиента служб Google Play и присоединить его к службам:
        googleApiClient = GoogleApiClient.Builder(this)
            .addApi(LocationServices.API)
            .addConnectionCallbacks(connectionCallBack)
            .build()

    }

}*/