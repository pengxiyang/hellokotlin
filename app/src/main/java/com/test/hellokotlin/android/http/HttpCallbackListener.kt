package com.test.hellokotlin.android.http

import java.lang.Exception

interface HttpCallbackListener {
    fun  onFinish(response:String)
    fun  onError(exception: Exception)
}