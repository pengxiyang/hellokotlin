package com.test.hellokotlin.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.test.hellokotlin.R
import com.test.hellokotlin.databinding.HandlerActivityBinding
import com.test.hellokotlin.service.LocationService
import java.lang.Exception
import kotlin.concurrent.thread

class HandlerActivity : AppCompatActivity(), View.OnClickListener {
    val updateText = 1
    private lateinit var mBinding: HandlerActivityBinding

    lateinit var  locationBinder:LocationService.LocationBinder

    val mHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                updateText -> mBinding.tv.text = msg.obj.toString()

            }
        }
    }

    private val  connection =object :ServiceConnection{

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            locationBinder =service as LocationService.LocationBinder
            locationBinder.startLocation()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            locationBinder.stopLocation()

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = HandlerActivityBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.startThreadBt.setOnClickListener(this)
        mBinding.startThreadKtBt.setOnClickListener(this)

        DownloadTask().execute()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.start_thread_bt -> {
                Thread {
                    val msg = Message()
                    msg.what = updateText
                    msg.obj = "开启线程了"
                    mHandler.sendMessage(msg)
                }.start()
            }
            R.id.start_thread_kt_bt -> {
                thread {
                    val msg = Message()
                    msg.what = updateText
                    msg.obj = "开启线程2"
                    mHandler.sendMessage(msg)
                }
            }
        }
    }


    /**
     * 绑定服务
     */
    private fun  bindService(){
        val  intent = Intent(this@HandlerActivity,LocationService::class.java)
        bindService(intent,connection,Context.BIND_AUTO_CREATE)

    }


    /**
     * 解绑服务
     */
    private fun  unBindService(){
        unbindService(connection)

    }




    /***
     * asyncTask 处理异步
     */
    class DownloadTask : AsyncTask<Unit, Int, Boolean>() {

        override fun onPreExecute() {
            //做准备

        }

        override fun doInBackground(vararg params: Unit?) = try {
            //做耗时操作
            while (true) {

            }
            true
        } catch (e: Exception) {
            false
        }

        override fun onProgressUpdate(vararg values: Int?) {
            //更新进度
        }

        override fun onPostExecute(result: Boolean?) {
            //提示处理结果
        }
    }
}