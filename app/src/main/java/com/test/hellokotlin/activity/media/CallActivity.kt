package com.test.hellokotlin.activity.media

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.hellokotlin.databinding.ActivityCallBinding

class CallActivity :AppCompatActivity() {
    lateinit var  binding:ActivityCallBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCallBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()

    }

    private fun initData() {

    }
}