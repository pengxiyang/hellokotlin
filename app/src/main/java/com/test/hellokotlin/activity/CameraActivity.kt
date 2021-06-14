package com.test.hellokotlin.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.hellokotlin.databinding.ActivityCallBinding
import com.test.hellokotlin.databinding.ActivityCameraBinding

class CameraActivity :AppCompatActivity() {
    lateinit var  binding:ActivityCameraBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()

    }

    private fun initData() {

    }
}