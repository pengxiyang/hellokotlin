package com.test.hellokotlin.android.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.test.hellokotlin.databinding.ActivityRunningBinding
import com.test.hellokotlin.android.service.MyService

class RunningActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRunningBinding
    lateinit var downloadBinder: MyService.DownloadBinder
    var i=0

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            downloadBinder = service as MyService.DownloadBinder
            downloadBinder.startDownload()
            downloadBinder.getProgress(mHandler)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.e("MyService", "onServiceDisconnected: ")
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRunningBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bindBt.setOnClickListener {
                val intent = Intent(this@RunningActivity, MyService::class.java)
                bindService(intent, connection, Context.BIND_AUTO_CREATE)//绑定service
            }

        binding.unbindBt.setOnClickListener {
            unbindService(connection)//解绑
        }
        binding.startBt.setOnClickListener {

         mHandler.sendEmptyMessage(1)
        }
        binding.stopBt.setOnClickListener {
            mHandler.removeMessages(1)
            binding.startBt.setText("继续")
        }

    }
     val mHandler =object :Handler(){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg.what){
                1-> {
                    i++
                    binding.contentTv.setText(msg.obj.toString())
                  //  sendEmptyMessageDelayed(1,1000)

                }


            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler.removeCallbacksAndMessages(null)
    }


}