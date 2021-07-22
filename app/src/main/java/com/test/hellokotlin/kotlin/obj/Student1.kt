package com.test.hellokotlin.kotlin.obj

/**
 *  created by pxy on 2021/4/21
 *  类中只有次构造函数 ,没有主构造函数就不用加（）,次构造函数只能直接调用父类的构造
 *  函数，this 关键字 换成了super
 *
 */
class Student1 :Person {

    constructor(name: String,age:Int) :super(name, age){

    }

}