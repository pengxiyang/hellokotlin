package com.test.hellokotlin.test.lambda

import com.test.hellokotlin.test.obj.Study
import kotlin.concurrent.thread

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

        Thread{

        }.start()

        //kotlin提供的更简洁的写法
        thread {

        }
    }

    /**
     * ?: 如果左边表达式的结果不为空就返回左边表达式的结果，否则返回右边表达式的结果 ?.用于判空
     */
    fun test(a: Int){
        val  b=1
        val  c =if(a!=null){
            a
        }else{
            b
        }
        //可以简化成
        val c1 =a?:b
    }


    fun  getTextLength(text:String?):Int{
        if(text!=null){
            return text.length
        }
        return 0
    }

    /**
     * ?: 如果左边表达式的结果不为空就返回左边表达式的结果，否则返回右边表达式的结果 ?.用于判空（对象为空时什么都不做）
     */
    fun getTextLength1(text: String?)=text?.length ?:0

    var content :String? ="hello"


    fun  test1(){
        if(content  !=null){
            printUpperCase()
        }
    }

    /**
     * 虽然上面已经判空，但是kotlin不能识别，需要使用!!（非空断言工具）,来告诉kotlin我确定这里不为null
     */
    fun  printUpperCase(){
        val upperCase =content!!.toUpperCase()
        println(upperCase)

    }
    /**---------------------let函数放在Student2里---------------------*/

    var study:Study? =null

    /**
     * 当Lambda 表达式的参数列表只有一个参数时，可以不用声明参数名，直接使用it关键字来替代
     *
     *let 函数可以处理全局变量的判空问题，而if语句无法做到
     */
    fun doStudy(){
        // if 不能处理全局变量为空
   /*     if(null!=study){
            study.readBooks()
            study.doHomework()
        }*/
        //第二种
        study?.readBooks()
        study?.doHomework()

        //第三种 使用let
        study?.let { stu ->
            stu.readBooks()
            stu.doHomework()
        }
        //第四种 进一步简化
        study?.let {
            it.readBooks()
            it.doHomework()
        }

    }

    /***------------------------字符串内嵌表达式----------------------------------*/


    fun  testString(){
        //原来的写法
        val  brand ="Samsung"
        val  price =1999
        println("Cellphone(brand="+brand+",price="+price+")")

        //字符串内嵌表达式写法
        println("Cellphone(brand=$brand,price =$price)")
    }

    /**
     * 给定参数设定默认值的方式
     */
    fun  pritParams(num:Int,str:String ="hello"){
        println("num is $num,str is $str")
    }

    /**
     *
     */
    fun  pritParams1(num:Int=100,str:String){
        println("num is $num,str is $str")
    }

    fun  pritParams2(num: Int=100,str: String="123"){
        println("num is $num, str is $str")

    }

    /**
     * 测试string方法
     */
    fun testStr(){
        pritParams(100)
        pritParams1(str ="123")
        pritParams1(100,"123")

    }




}