package com.test.hellokotlin.http

import java.lang.Exception

interface HttpCallbackListener {
    fun  onFinish(response:String)
    fun  onError(exception: Exception)
}