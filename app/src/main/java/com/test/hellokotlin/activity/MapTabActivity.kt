package com.test.hellokotlin.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.esri.arcgisruntime.ArcGISRuntimeEnvironment
import com.test.hellokotlin.databinding.ActivityMapTabBinding
import com.test.hellokotlin.jump2MapActivity

/**
 *  created by pxy on 2021/4/25
 *
 */
class MapTabActivity :AppCompatActivity() {
    private lateinit var  binding: ActivityMapTabBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapTabBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btToMap.setOnClickListener {
           jump2MapActivity(this)

        }

    }
}