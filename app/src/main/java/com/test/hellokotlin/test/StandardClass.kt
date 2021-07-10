package com.test.hellokotlin.test

import java.lang.StringBuilder

/**
 *  created by pxy on 2021/4/23
 *标准函数  let  with  run apply
 *
 * 他们的唯一目的是在对象的上下文中执行代码块。当对一个对象调用这样的函数并提供一个lambda表达式时，
 * 他会形成一个临时作用域，在此作用域中，可以访问对象而无需其名称，这些函数被称为作用域函数。
 *
 * 作用域函数是为了方便对一个对象进行访问和操作，你可以对它进行空检查或修改它的属性或直接返回他的值
 * 等操作。
 *
 */
class StandardClass {
    val list = listOf("apple","pear","banana","orange","grape")


    class  Book(){
        var name ="数据结构"
        var price =60
        fun  displayInfo()= print("Book name :$name and price:$price")
    }

    /**
     * let 是参数类型化T的扩展函数，在let块内可以通过it指代该对象
     * 返回值为le块的最后一行或者指定return表达式
     * 最后一行是非赋值语句，返回非赋值语句
     * 最后一行是赋值语句，返回Unit类型
     */
    fun letTest(){
        //第一点
        val  book=Book().let {
            it.name ="设计模式"
            "This book is ${it.name}"
        }
        print(book)//控制台输出 This book is 计算机网络

        /**-----------完美的分割线-----------------------------*/
        //第二点 空安全检查
        var name:String?=null
        val  nameLength =name?.let {
            it.length
        }?:"name为空的值"
        print(nameLength)

        /**------------------------------------------------------------------------------------*/
        //第三点  可对调用链的结果进行操作
        //原来的写法
        val numbers = mutableListOf("one","two","three","four")
        val  result =numbers.map {  it.length }.filter { it>3 }
        print(result)
        //使用let,去掉变量赋值这一步
        numbers.map { it.length }.filter { it>3  }.let {
            print(it)
        }

        /**---------------------------------------------------------------------------**/
        //第四点 let 可以把 it重命名为一个可读的lambda参数
        val  book1 =Book().let {book ->
        book.name ="软件测试"
        }
        print(book1)




    }



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
     调用with 函数并传入StringBuilder对象
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
     * 和with一样 会使用Lambda表达式的最后一行代码作为返回值返回
     * 调用StringBuilder的run方法
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
     * val result =obj.apply{//这里是obj的上下文 }  //result =obj
     * apply函数无法指定返回值，只能返回调用对象本身，
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