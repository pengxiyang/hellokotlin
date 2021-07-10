package com.test.hellokotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Main2ViewModel(countReserved: Int) : ViewModel() {

    /**
     * 把counter变量修改成了一个MutableLiveData对象，并指定泛型为Int，他是一个可变的LiveData
     * 主要有三种读写数据的方法 getValue setValue(给LiveData设置数据，只能在主线程中调用)  postValue(设置数据,在非主线程中调用)
     *
     *
     *
     */
    val counter = MutableLiveData<Int>()


    init {
        counter.value = countReserved
    }

    fun plusOne() {
        val count = counter.value ?: 0
        counter.value =count+1
    }

    fun clear() {
        counter.value=0

    }
}