package com.test.hellokotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class Main2ViewModelFactory(private val  countReserved:Int):ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return Main2ViewModel(countReserved) as T
    }
}