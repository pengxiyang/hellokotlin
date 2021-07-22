package com.test.hellokotlin.kotlin.numobj;

/**
 * created by pxy on 2021/4/21
 */
public class Singleton1 {
    private static Singleton1 instance;

    public synchronized static  Singleton1 getInstance(){
        if(instance==null){
            instance =new Singleton1();
        }
        return instance;
    }

    public void singleTonTest(){
        System.out.println("singletonTest is called.");
    }
}
