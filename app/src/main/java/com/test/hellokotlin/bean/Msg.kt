package com.test.hellokotlin.bean

/**
 *  created by pxy on 2021/4/26
 *
 */
class Msg(val content:String,val type:Int) {
    companion object{
        const val TYPE_RECEIVED = 0
        const val TYPE_SENT = 1
    }
}