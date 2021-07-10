package com.test.hellokotlin.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.hellokotlin.databinding.ActivityFruitMaterialBinding

class MaterialDesign2Activity :AppCompatActivity(){
    private lateinit var  binding:ActivityFruitMaterialBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFruitMaterialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun  initView(){

    }

}