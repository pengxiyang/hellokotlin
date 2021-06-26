package com.test.hellokotlin.holder.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView


abstract class BaseHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    var mOnItemClickListener: OnViewClickListener? = null

    /**
     * 设置数据
     */
    abstract fun setData(data: T, position: Int)

    override fun onClick(v: View?) {
        if (null != mOnItemClickListener) {
            mOnItemClickListener!!.onViewClick(v,this.position)
        }
    }

    fun setOnItemClickListener(listener: OnViewClickListener?) {
        mOnItemClickListener = listener

    }
    fun onRelease(){

    }

    /**
     * item点击事件
     */
    interface OnViewClickListener {
        fun onViewClick(view: View?, position: Int)
    }

    init {
        itemView.setOnClickListener(this)
    }
}