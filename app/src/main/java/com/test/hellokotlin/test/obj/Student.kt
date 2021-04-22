package com.test.hellokotlin.test.obj

/**
 *  created by pxy on 2021/4/21
 *
 *kotlin中存在主构造函数(默认不带参数的 ,没有函数体)和次构造函数
 */
class Student(val sno:String,val grade:Int, name:String, age:Int): Person(name,age) {

    /**
     *  kotlin为主构造函数提供了一个init 结构体
     */
    init {
        println("sno is "+sno)
        println("grade is "+grade)
    }

    /**
     * kotlin规定 一个类如果既有主构造函数，又有次构造函数，所有的次构造函数必须调用主构造函数（包括间接调用）
     */
    constructor(name: String,age: Int):this("",0,name,age){
    }


    constructor():this("",0){

    }

    /**
     * 次构造函数的定义让我们拥有多个方式对当前类进行初始化
     */
    val  student =Student("123",98,"Jack",20)
    val  student1 = Student()
    val  student2 =Student("Jack",19)


}