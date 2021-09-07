package com.test.hellokotlin.kotlin

import kotlinx.coroutines.*

/**
 * 协程
 */
class CoroutinesTest {

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val  co =CoroutinesTest()
          //  co.main()
            co.main1()
            co.main2()
            co.main3()
            co.main4()
            co.main5()


        }
    }

    fun  main(){
        //每次创建一个顶层携程
        GlobalScope.launch {
            println("codes run in coroutine scope")
            delay(1500)
            println("codes run in coroutine scope finished")

        }
        Thread.sleep(1000)
        println("*-------------------------------------------------------*")


    }
    fun main1(){
        //只应该在测试环境使用，正式环境容易产生性能上的问题
        runBlocking {
            println("codes run in coroutine scope")
            delay(1500)
            println("codes run in coroutine scope finished")
        }

    }
    fun  main2(){
        runBlocking {
            launch {
                println("launch1")
                delay(1000)
                println("launch1 finished")
            }
            launch {
                println("launch2")
                delay(1000)
                println("launch2 finished")
            }
        }
    }

    fun  main3(){
        val  start =System.currentTimeMillis()

        runBlocking {
            repeat(100000){
                launch {
                    println(".")
                }
            }
        }
        val  end =System.currentTimeMillis()
        println(end-start)
    }
    //coroutineScope可以保证其作用域内的所有代码和子协程在全部执行完之前，外部的协程回一直呗挂起
  fun main4(){
        suspend fun printDot() = coroutineScope {
            launch {
                println(".")
                delay(1000)
            }
        }

    }

    fun  main5(){
        runBlocking {
            coroutineScope {
                launch {
                    for(i in  1..10){
                        println(i)
                        delay(1000)
                    }
                }
            }
            println("coroutineScope finished")
        }
        println("runBlocking finished")
    }
}

