package com.test.hellokotlin.test.lambda

/**
 *  created by pxy on 2021/4/21
 *集合的函数式api
 */
class classApi {
    val list = listOf("apple", "banana", "pear", "watermelon")

    /**
     * 正常写法
     */
    fun maxLength(): String {
        var maxlengthFruit = ""

        for (fruit in list) {
            if (fruit.length > maxlengthFruit.length) {
                maxlengthFruit = fruit;
            }
        }
        return maxlengthFruit
    }

    /**
     * 函数APi
     */
    fun maxLength1() = list.maxBy { it.length }

    /**
     * 大写
     */
    fun toUpperCase() {
        val list1 = list.map { it.toUpperCase() }
        for (fruit in list1) {
            println(fruit)
        }
    }

    /**
     * filter
     */
    fun filter() {
        val newList = list.filter { it.length <= 5 }.map { it.toUpperCase() }
        for (fruit in newList) {
            println(fruit)
        }
    }

    /**
     * any 和all api
     */
    fun  test(){
        println("any ="+list.any{ it.length<=5}+"----all="+list.all { it.length<=5 })
    }

    fun  createThread(){
        //第一种写法
        Thread(object :Runnable{
            override fun run() {
              println("123")
            }
        } ).start()
        //第二种
        Thread(Runnable {
            println("123")
        }).start()

        //第三种
        Thread({
            println("12")
        }).start()
    }

}