package com.test.hellokotlin.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.hellokotlin.databinding.MsgLeftItemBinding
import com.test.hellokotlin.databinding.MsgRightItemBinding

/**
 *  created by pxy on 2021/4/27
 *
 */
sealed class MsgViewHolder(view:View):RecyclerView.ViewHolder(view)

 class LeftViewHolder(binding: MsgLeftItemBinding):MsgViewHolder(binding.root){
    val  leftMsgTv: TextView =binding.leftMsg

}

 class RightViewHolder(bind: MsgRightItemBinding):MsgViewHolder(bind.root){
    val  rightMsgTv: TextView =bind.rightMsg
}