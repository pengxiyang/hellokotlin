package com.test.hellokotlin.android.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class LocationService : Service() {
    private val mBinder = LocationBinder()

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder? =
        mBinder


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    inner class LocationBinder : Binder() {

        fun startLocation() {

        }

        fun stopLocation() {

        }

    }


}