package com.test.hellokotlin.android.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.test.hellokotlin.android.bean.Fruit
import com.test.hellokotlin.databinding.ItemFruitBinding

/**
 *  created by pxy on 2021/4/25
 *
 *
 */
class FruitAdapter(activity:Activity,val resId:Int,data:List<Fruit>):ArrayAdapter<Fruit>(activity,resId,data) {

    inner  class  ViewHolder(binding: ItemFruitBinding){
        val iv:ImageView =binding.fruitIv
        val tv:TextView =binding.fruitNameTv


    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view:View
        val  viewHolder:ViewHolder
        val binding :ItemFruitBinding
        if(convertView==null){
            binding =ItemFruitBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            view=binding.root
            viewHolder =ViewHolder(binding)
            view.tag =viewHolder
        }else{
            view =convertView
            viewHolder =view.tag as ViewHolder

        }
        val  fruit =getItem(position)
        if (fruit!=null){
           viewHolder.iv.setImageResource(fruit.imageId)
            viewHolder.tv.text =fruit.name
        }
        return view
    }







}