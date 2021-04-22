package com.test.hellokotlin.test.numobj

/**
 *  created by pxy on 2021/4/21
 * 单例类 obj 是关键字 不需要私有化构造函数，也不需要提供getInstance()方法
 */
object Singleton {
    fun singletonTest(){
        println("singletonTest is called.")
    }

    /**       java    修饰符
     * public 所有类可见    private 当前类可见 protected 当前类、子类、同一包路径下的类可见 default（默认） 同一包路径下的类可见
     *
     *      kotlin  修饰符
     *      public （默认） 所有类可见 private 当前类可见 protected 当前类、子类可见  internal 同一模块中的类可见
     *
     */
}