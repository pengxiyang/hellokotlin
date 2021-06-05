package com.test.hellokotlin.activity

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.test.hellokotlin.databinding.ActivityRunningBinding
import com.test.hellokotlin.service.RunningService

class RunningActivity :AppCompatActivity(),ServiceConnection{
    private lateinit var binding: ActivityRunningBinding
    lateinit var runingBinder:RunningService.MyBinder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityRunningBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        TODO("Not yet implemented")
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        TODO("Not yet implemented")
    }

}