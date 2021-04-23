package com.test.hellokotlin.test

import java.lang.StringBuilder

/**
 *  created by pxy on 2021/4/23
 *标准函数  let  with  run apply
 */
class StandardClass {
    val list = listOf("apple","pear","banana","orange","grape")

    /**
     * 原始写法
     */
    fun test(){
        val  builder =StringBuilder()
        builder.append("Start eating fruits.\n")
        for (fruit in list){
            builder.append(fruit).append("\n")
        }
        builder.append("Ate all fruits")
        val  result =builder.toString()
        println(result)
    }
    /** with（）括号里传入的对象 是整个lambda表达式的上下文
     * with函数让代码更加精简
     *   val result = with(obj){
         //这里obj时上下文
         "value" //with函数的返回值}
     *
     */
    fun test1(){
        val  result = with(StringBuilder()){
            append("Start eating fruits.\n")
            for (fruit in list){
                append(fruit).append("\n")
            }
            append("Ate all fruits")
            toString()

        }

        println(result)

    }




    /**
     * run 函数
     *
     * val result =obj.run{//这里是obj的上下文 ，"value"  run函数的返回值   }
     */
    fun  test2(){
        val  result =StringBuilder().run {
            append("Start eating fruits.\n")
            for (fruit in list){
                append(fruit).append("\n")
            }
            append("Ate all fruits")
            toString()
        }
        println(result)

    }

    /**
     * apply
     */
    fun test3(){
       val  result =StringBuilder().apply {
           append("Start eating fruits.\n")
           for (fruit in list){
               append(fruit).append("\n")
           }
           append("Ate all fruits")
       }
        println(result.toString())
    }


}