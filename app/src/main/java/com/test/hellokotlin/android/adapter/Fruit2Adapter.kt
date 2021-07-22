package com.test.hellokotlin.android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.test.hellokotlin.android.bean.Fruit
import com.test.hellokotlin.databinding.ItemFruitBinding

/**
 *  created by pxy on 2021/4/22
 *
 *
 */
class Fruit2Adapter(val list: List<Fruit>) : RecyclerView.Adapter<Fruit2Adapter.ViewHolder>() {


    inner class ViewHolder(binding: ItemFruitBinding) : RecyclerView.ViewHolder(binding.root) {
        val iv: ImageView = binding.fruitIv
        val tv: TextView = binding.fruitNameTv


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFruitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ViewHolder(binding)
        viewHolder.itemView.setOnClickListener {
            val  position= viewHolder.adapterPosition
            val  fruit =list[position]
            Toast.makeText(parent.context,"you clicked image${fruit.name}",Toast.LENGTH_SHORT).show()
        }
        return viewHolder
    }



//    val count:Int
//    get() = list.size

    override fun getItemCount() = list?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = list[position]
        holder.iv.setImageResource(fruit.imageId)
        holder.tv.text = fruit.name


    }

}