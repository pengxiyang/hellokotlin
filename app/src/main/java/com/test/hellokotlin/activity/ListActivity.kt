package com.test.hellokotlin.activity

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.test.hellokotlin.databinding.ActivityListViewBinding

/**
 *  created by pxy on 2021/4/23
 *
 */
class ListActivity:AppCompatActivity() {
    private lateinit var  binding: ActivityListViewBinding
    private val  data= listOf("apple","banana","orange","watermelon","pear","grape","pineapple","strawberry","cherry","mango","apple","pear")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val  adapter =ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data)
        binding.listView.adapter =adapter


    }


}