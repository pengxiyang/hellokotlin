package com.test.hellokotlin.test.obj

/**
 *  created by pxy on 2021/4/21
 *
 */
class Student2(name: String,age:Int):Person(name ,age),Study{
    fun main(){
        val student =Student2("Jack",20)

        doStudy(student)

    }


    fun doStudy(study: Study?){
        //第一种
       /* if(null!=study){
            study.readBooks()
            study.doHomework()
        }*/
        //第二种
        study?.readBooks()
        study?.doHomework()
    }

    /**
     * ?: 如果左边表达式的结果不为空就返回左边表达式的结果，否则返回右边表达式的结果
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

    fun getTextLength1(text: String?)=text?.length ?:0


    override fun readBooks() {
     println(name+" is  reading")
    }

    override fun doHomework() {
      println(name+" is doing homework.")
    }

}