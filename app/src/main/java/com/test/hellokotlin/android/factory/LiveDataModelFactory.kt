package com.test.hellokotlin.android.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.hellokotlin.android.viewmodel.LiveDataModel

class LiveDataModelFactory(private val  countReserved:Int):ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LiveDataModel(countReserved) as T
    }
}