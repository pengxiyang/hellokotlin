package com.test.hellokotlin.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.hellokotlin.R
import com.test.hellokotlin.adapter.Fruit2Adapter
import com.test.hellokotlin.bean.Fruit
import com.test.hellokotlin.databinding.ActivityRecyclerViewBinding


/**
 *  created by pxy on 2021/4/26
 *
 */
class RecyclerViewActivity :AppCompatActivity() {
    private lateinit var  binding: ActivityRecyclerViewBinding
    private val  fruitList =ArrayList<Fruit>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFruits()
        val  layoutManager =LinearLayoutManager(this)
        binding.rv.layoutManager =layoutManager
        val  adapter =Fruit2Adapter(fruitList)
        binding.rv.adapter =adapter

    }
    private fun initFruits(){
        repeat(2){
            fruitList.add(Fruit("Apple", R.mipmap.ic_launcher))
            fruitList.add(Fruit("Banana", R.mipmap.ic_launcher))
            fruitList.add(Fruit("orange", R.mipmap.ic_launcher))
            fruitList.add(Fruit("pear", R.mipmap.ic_launcher))
            fruitList.add(Fruit("cherry", R.mipmap.ic_launcher))
            fruitList.add(Fruit("mango", R.mipmap.ic_launcher))

        }
    }
}