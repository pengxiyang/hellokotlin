package com.test.hellokotlin.test.lambda

/**
 *  created by pxy on 2021/4/21
 *
 *  list  set  map 的初始化
 *
 */
class Test {
    /**
     * list的初始化
     */
    fun test () {
        //类似java中的初始化
        val  list =ArrayList<String>()
        list.add("apple")
        //kotlin 提供listOf()方法  创建一个不可变的集合
        val  list1 = listOf("apple","banana")
        for(fruit in list1){
            println(fruit)

        }
    }


    /**
     * 创建一个可变的集合
     */
    fun  test1(){
        val list = mutableListOf("apple","orange","banana")
        list.add("pear")
        for (fruit in list){
            println(fruit)
        }
    }


    /**
     * set的初始化
     */
    fun test2(){
        val  set = setOf("apple","pear")
        val  set1 = mutableSetOf("apple","pear")
        set1.add("banana")
        for (fruit in set){
            println(fruit)
        }
        for (fruit in set1){
            println(fruit)
        }
    }

    /**
     *map
     */
    fun test3(){
        val  map =HashMap<String ,Int>()
        map.put("apple",1 ) //kotlin中不建议这么写

        map["pear"] = 2//推荐这种类似数组下标的写法
        val num =map["apple"]

        val  map1 = mapOf("apple" to 1 ,"pear" to 2)

        //集合的遍历
        for((fruit,number) in map1){
            println(fruit+"----"+number)
        }
    }

}