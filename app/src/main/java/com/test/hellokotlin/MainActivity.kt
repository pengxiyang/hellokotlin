package com.test.hellokotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.test.hellokotlin.android.activity.DialogActivity
import com.test.hellokotlin.android.activity.SecondActivity
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
        supportActionBar?.hide()//隐藏系统自带标题栏
        binding.bt.setOnClickListener {
           //SecondActivity.actionStart(this,"","")
            StartActivityUtils.actionStart(this,"","")
        }
        binding.text.setOnClickListener {
            jump2DialogActivity()
        }
        binding.btDialog.setOnClickListener {
            jump2AlertDialogActivity(this)
        }
        binding.btListview.setOnClickListener {
            jump2ListActivity(this)
        }
        binding.btRv.setOnClickListener {
            jump2RecyclerViewActivity(this)
        }
        binding.btChat.setOnClickListener {
            jump2ChatActivity(this)
        }
        binding.btMap.setOnClickListener {
            jump2MapTabActivity(this)
        }
        binding.btFragment.setOnClickListener {
            jump2TestFragmentActivity(this)

        }
        binding.actServiceBt.setOnClickListener {
            jumpToRunningActivity(this)

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