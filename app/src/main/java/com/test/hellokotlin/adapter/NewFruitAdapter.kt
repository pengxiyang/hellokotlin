package com.test.hellokotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.hellokotlin.bean.Fruit
import com.test.hellokotlin.databinding.FruitItemBinding

class NewFruitAdapter (val context:Context,val  list:List<Fruit>):RecyclerView.Adapter<NewFruitAdapter.ViewHolder>(){

    inner class  ViewHolder(binding:FruitItemBinding):RecyclerView.ViewHolder(binding.root){
        val  fruitImage =binding.fruitImage
        val  fruitName =binding.fruitName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         val binding = FruitItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val viewHolder =ViewHolder(binding)

        return viewHolder

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val  fruit =list[position]
        holder.fruitName.text =fruit.name
        Glide.with(context).load(fruit.imageId).into(holder.fruitImage)
    }

    override fun getItemCount(): Int =list?.size?:0
}