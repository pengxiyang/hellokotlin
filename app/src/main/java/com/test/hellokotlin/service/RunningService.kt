package com.test.hellokotlin.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class RunningService :Service() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int = START_STICKY

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
   inner class MyBinder(private val runningService: RunningService):Binder(){

        fun  getRunningService():RunningService=runningService
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}