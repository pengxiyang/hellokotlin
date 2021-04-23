package com.test.hellokotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.hellokotlin.databinding.ItemTestBinding

/**
 *  created by pxy on 2021/4/22
 *
 *
 */
class TestAdapter(val list:List<String>):RecyclerView.Adapter<TestAdapter.ViewHolder>(){


    inner class ViewHolder(binding: ItemTestBinding) :RecyclerView.ViewHolder(binding.root){
        val tv:TextView =binding.tv
        val  bt:Button =binding.bt


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val  binding =ItemTestBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount()=list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val string =list.get(position)
        holder.tv.text =string

    }

}