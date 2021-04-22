package com.test.hellokotlin.test

/**
 *  created by pxy on 2021/4/21
 *  循环 circulation
 */
class Circulation {
    /**
     * 区间的概念.. 两端关闭
     */
    fun test() {
        for (i in 0..10){
         println(i)
        }
    }

    /**
     * until 前闭后开  step 表示跳过
     */
    fun test1() {
        for (i in 0 until 10 step 2){
            println(i)

        }
    }


    /**
     * downTo 表示降序
     */
    fun test2() {
        for (i in 10 downTo 1){
            println(i)
        }

    }
}