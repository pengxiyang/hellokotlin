package com.test.hellokotlin.test.obj

/**
 *  created by pxy on 2021/4/22
 * 函数设定默认参数值 可以很大程度上替代次构造函数
 */
class Student3(val sno:String="",val grade :Int=0,name:String="",age:Int=0):Person(name, age) {

    /**
     * 次构造函数的定义让我们拥有多个方式对当前类进行初始化
     */
    val  student =Student3("123",98,"Jack",20)
    val  student1 = Student3()
    val  student2 =Student3("Jack",19)
}