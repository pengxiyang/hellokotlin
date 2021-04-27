package com.test.hellokotlin.common

import java.lang.Exception

/**
 *  created by pxy on 2021/4/27
 *  密封类 sealed class 是关键字
 *
 */
sealed class  Result
class Success(val msg:String):Result()
class Failure(val error:Exception):Result()

fun getResultMsg(result: Result)=when(result){
    is  Success->result.msg
    is  Failure-> "Error is ${result.error.message}"

}