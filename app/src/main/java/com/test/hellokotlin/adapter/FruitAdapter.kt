package com.test.hellokotlin.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.test.hellokotlin.bean.Fruit
import com.test.hellokotlin.databinding.ItemFruitBinding

/**
 *  created by pxy on 2021/4/25
 *
 *
 */
class FruitAdapter(activity:Activity,val resId:Int,data:List<Fruit>):ArrayAdapter<Fruit>(activity,resId,data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding =ItemFruitBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val  fruit =getItem(position)
        if (fruit!=null){
            binding.fruitNameTv.text =fruit.name
        }

        return binding.root
    }




}