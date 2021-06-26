package com.test.hellokotlin.adapter.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.hellokotlin.holder.base.BaseHolder

abstract class DefaultAdapter<T>(var infos: MutableList<T>) :
    RecyclerView.Adapter<BaseHolder<T>>() {

    var mOnItemClickListener: OnRecyclerViewItemClickListener<T>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<T> {
        val view1 =
            LayoutInflater.from(parent.context).inflate(getLayoutId(viewType), parent, false)
        val mHolder: BaseHolder<T> = getHolder(view1, viewType)
        mHolder.setOnItemClickListener(object : BaseHolder.OnViewClickListener {
            override fun onViewClick(view: View?, position: Int) {
                if (null != mOnItemClickListener && infos.size > 0) {
                    mOnItemClickListener!!.onItemClick(view, viewType, infos[position], position)
                }
            }

        })
        return mHolder
    }

    override fun getItemCount(): Int = infos.size ?: 0

    override fun onBindViewHolder(holder: BaseHolder<T>, position: Int) {
        holder.setData(infos[position], position)
    }

    fun getItem(position: Int): T? = infos[position]

    abstract fun getHolder(v: View, viewType: Int): BaseHolder<T>

    abstract fun getLayoutId(viewType: Int): Int

    fun setOnItemClickListener(listener: OnRecyclerViewItemClickListener<T>?) {
        this.mOnItemClickListener = listener
    }

    interface OnRecyclerViewItemClickListener<T> {
        fun onItemClick(v: View?, viewType: Int, data: T, position: Int)
    }

    companion object{
        fun  releaseAllHolder(recyclerView: RecyclerView?){
            if(recyclerView==null){
                return
            }
            for (i in recyclerView.childCount-1 downTo 0){
                val  view =recyclerView.getChildAt(i)
                val  viewHolder =recyclerView.getChildViewHolder(view)
                if(viewHolder is BaseHolder<*>){
                    viewHolder.onRelease()
                }
            }

        }
    }
}