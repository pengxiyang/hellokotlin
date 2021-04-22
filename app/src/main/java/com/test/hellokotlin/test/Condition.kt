package com.test.hellokotlin.test

/**
 *  created by pxy on 2021/4/21
 * if and when
 */
class Condition {
    /**
     * var 声明变量 val 声明常量 相当于java中的final
     */
    fun main() {
        val a: Int = 10
        var b: Int = 10;
        b = b * 10

        println("b=" + b)
    }

    fun largeNum(num1: Int, num2: Int): Int {
        var value = 0;
        if (num1 > num2) {
            value = num1
        } else {
            value = num2
        }
        return value
    }

    fun largeNum1(num1: Int, num2: Int): Int {
        val value = if (num1 > num2) {
            num1
        } else {
            num2
        }
        return value
    }

    fun largeNum2(num1: Int, num2: Int): Int {
        return if (num1 > num2) {
            num1
        } else {
            num2
        }
    }

    fun largeNum3(num1: Int, num2: Int) = if (num1 > num2) {
        num1
    } else {
        num2
    }

    /**
     * 最简洁的写法
     */
    fun largeNum4(num1: Int, num2: Int) = if (num1 > num2) num1 else num2


  //  fun  largeNum5(num1: Int,num2: Int)=

    /*-------------------------------------------------------------when-------------------------------*/
    /**
     * 精确匹配
     */
    fun getScore(name: String) = when (name) {
        "Tom" -> 86
        "jack" -> 77
        "Lisa" -> 95
        else -> 0

    }

    /**
     * kotlin中判断字符串和对象是否相等用==
     */
    fun getScore1(name: String) = when {
        name == "Tom" -> 86
        name == "jack" -> 77
        name == "Lisa" -> 95
        else -> 0

    }

    /**
     * 类型匹配
     */
    fun checkNum(num: Number) {
        when (num) {
            is Int -> println("number is Int")
            is Double -> println("number is Double")
            else -> println("number not support")
        }

    }


}