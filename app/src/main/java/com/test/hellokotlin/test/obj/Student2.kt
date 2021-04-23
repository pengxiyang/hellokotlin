package com.test.hellokotlin.test.obj

/**
 *  created by pxy on 2021/4/21
 *
 */
class Student2(name: String,age:Int):Person(name ,age),Study{
    var study:Study? =null
    fun main(){
        val student =Student2("Jack",20)

        doStudy(student)
        doStudy(study)

    }


    /**
     * 当Lambda 表达式的参数列表只有一个参数时，可以不用声明参数名，直接使用it关键字来替代
     *
     *let 函数可以处理全局变量的判空问题，而if语句无法做到
     */
    fun doStudy(study: Study?){
        //第一种
        if(null!=study){
            study.readBooks()
            study.doHomework()
        }
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

    /**
     * let 函数可以处理全局变量的判空问题，而if语句无法做到
     */
    fun doStudy1(){
        //第一种
//        if(null!=study){
//            study.readBooks()
//            study.doHomework()
//        }

    }


    override fun readBooks() {
     println(name+" is  reading")
    }

    override fun doHomework() {
      println(name+" is doing homework.")
    }

}