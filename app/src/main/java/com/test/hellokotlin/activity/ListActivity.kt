package com.test.hellokotlin.activity

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.test.hellokotlin.R
import com.test.hellokotlin.adapter.FruitAdapter
import com.test.hellokotlin.bean.Fruit
import com.test.hellokotlin.databinding.ActivityListViewBinding

/**
 *  created by pxy on 2021/4/23
 *
 */
class ListActivity:AppCompatActivity() {
    private lateinit var  binding: ActivityListViewBinding
    private val  data= listOf("apple","banana","orange","watermelon","pear","grape","pineapple","strawberry","cherry","mango","apple","pear")
    val  fruitList =ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFruits()
    //    val  adapter =ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data)
        val adapter =FruitAdapter(this,R.layout.item_fruit,fruitList)
        binding.listView.adapter =adapter
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