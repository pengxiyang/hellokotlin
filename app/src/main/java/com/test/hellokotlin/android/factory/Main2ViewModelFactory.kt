package com.test.hellokotlin.android.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.hellokotlin.android.viewmodel.Main2ViewModel

class Main2ViewModelFactory(private val  countReserved:Int):ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return Main2ViewModel(countReserved) as T
    }
}