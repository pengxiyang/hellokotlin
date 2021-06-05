package com.test.hellokotlin.service

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.*
import android.util.Log
import kotlin.concurrent.thread

class MyService : Service() {
    private val mBinder = DownloadBinder()
    private var clientHandler: Handler? = null
    var i=0

    companion object {
        val TAG = MyService::class.java.simpleName
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate executed ")
        //通知
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    }

    val mHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if(null!=clientHandler){
                val  message =clientHandler!!.obtainMessage(1)
                message.obj=i++
                clientHandler!!.sendMessage(message)

            }
            sendEmptyMessageDelayed(1,1000)

        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand executed ")
        thread {
            stopSelf()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return mBinder

    }

  inner  class DownloadBinder : Binder() {
        fun startDownload() {
            Log.d(TAG, "startDownload executed ")
        }

        fun getProgress(handler: Handler?): Int {
            Log.d(TAG, "getProgress executed")
            if(null!=handler){
                clientHandler =handler
            }
            mHandler.sendEmptyMessage(1)
            return 0
        }

        fun stop(){

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy executed ")
    }
}