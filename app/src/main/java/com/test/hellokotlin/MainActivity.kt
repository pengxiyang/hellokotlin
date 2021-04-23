package com.test.hellokotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.test.hellokotlin.activity.DialogActivity
import com.test.hellokotlin.activity.SecondActivity
import com.test.hellokotlin.databinding.ActivityMainBinding

/**
 * 不希望布局文件生成对应的binding类 使用 tools:viewBindingIgnore="true"
 */

class MainActivity : AppCompatActivity() {
    private val TAG = "---MainActivity"
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate: " )
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ActivityCollector.addActivity(this)
        binding.text.setOnClickListener {
           SecondActivity.actionStart(this,"","")
        }
        binding.bt.setOnClickListener {
            jump2DialogActivity()
        }
    }


    /**
     * 显示intent
     */
    fun jump2SecondActivity(){
        val intent =Intent(this,SecondActivity::class.java)
        startActivity(intent)
    }

    fun jump2SecondAc(){
        val  intent =Intent("com.test.hellokotlin.ACTION_START")
        startActivity(intent)
    }

    fun  jump2DialogActivity(){

        val intent =Intent(this,DialogActivity::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart: " )
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(TAG, "onRestart: " )

    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume: " )
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause: " )
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop: " )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: " )
        ActivityCollector.removeActivity(this)
    }

    /**
     * 杀死app
     */
    fun  killApp(){
        android.os.Process.killProcess(android.os.Process.myPid())
    }

}