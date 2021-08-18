package com.test.hellokotlin.android.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.hellokotlin.MainActivity
import com.test.hellokotlin.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {
    private lateinit var  binding :ActivityTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}