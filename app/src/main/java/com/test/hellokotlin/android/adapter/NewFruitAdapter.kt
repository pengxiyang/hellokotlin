package com.test.hellokotlin.android.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.hellokotlin.android.activity.MaterialDesign2Activity
import com.test.hellokotlin.android.bean.Fruit
import com.test.hellokotlin.databinding.FruitItemBinding

class NewFruitAdapter (val context:Context,val  list:List<Fruit>):RecyclerView.Adapter<NewFruitAdapter.ViewHolder>(){

    inner class  ViewHolder(binding:FruitItemBinding):RecyclerView.ViewHolder(binding.root){
        val  fruitImage =binding.fruitImage
        val  fruitName =binding.fruitName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         val binding = FruitItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val holder =ViewHolder(binding)
        holder.itemView.setOnClickListener{
            //这里要回到第一个activity，所以杀死中间其他activity

          //  MyApplcation.destoryActivity("1")

            val position =holder.adapterPosition
            val  fruit =list[position]
            val  intent =Intent(context,MaterialDesign2Activity::class.java).apply {
                putExtra(MaterialDesign2Activity.FRUIT_NAME,fruit.name)
                putExtra(MaterialDesign2Activity.FRUIT_IMAGE_ID,fruit.imageId)
            }
            context.startActivity(intent)
        }

        return holder

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val  fruit =list[position]
        holder.fruitName.text =fruit.name
        Glide.with(context).load(fruit.imageId).into(holder.fruitImage)
    }

    override fun getItemCount(): Int =list?.size?:0
}