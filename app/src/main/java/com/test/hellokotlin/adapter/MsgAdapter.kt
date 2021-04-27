package com.test.hellokotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.hellokotlin.bean.Msg
import com.test.hellokotlin.databinding.MsgLeftItemBinding
import com.test.hellokotlin.databinding.MsgRightItemBinding
import com.test.hellokotlin.holder.LeftViewHolder
import com.test.hellokotlin.holder.RightViewHolder

/**
 *  created by pxy on 2021/4/27
 *
 */
class MsgAdapter(val msgList:List<Msg>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    //用密封类替换
  /*  inner class LeftViewHolder(binding: MsgLeftItemBinding):RecyclerView.ViewHolder(binding.root){
        val  leftMsgTv:TextView =binding.leftMsg

    }

    inner class RightViewHolder(bind:MsgRightItemBinding):RecyclerView.ViewHolder(bind.root){
        val  rightMsgTv:TextView =bind.rightMsg
    }
*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =if(viewType==Msg.TYPE_RECEIVED){
        val  binLeftItemBinding =MsgLeftItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        LeftViewHolder(binLeftItemBinding)
    }else{
        val  binRightItemBinding=MsgRightItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        RightViewHolder(binRightItemBinding)

    }

    override fun getItemCount()=msgList.size
    override fun getItemViewType(position: Int): Int {
        val msg =msgList[position]
        return msg.type
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val  msg =msgList[position]
        when(holder){
            is LeftViewHolder -> holder.leftMsgTv.text =msg.content
            is RightViewHolder -> holder.rightMsgTv.text =msg.content
        }
    }
}