package com.test.hellokotlin.java;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 引用
 */
public class ReferenceClass {
    public static volatile ReferenceClass instance;

    private ReferenceClass() {

    }

    public static ReferenceClass getInstance() {
        if (instance == null) {
            synchronized (ReferenceClass.class) {
                if (instance == null) {
                    instance = new ReferenceClass();
                }
            }
        }

        return instance;
    }


    /**
     * 强引用
     * 一般情况下。不会被回收，内存不足时，抛出OOM
     */
    public void strongReference() {
        String s = new String("Hello");
        s = null;//
        System.gc();//垃圾回收
        System.out.println(s);

        //输出结果是null
        //对象只有在创建他的方法执行结束才会被回收，或者主动设置obj =null
    }


    /**
     * 软引用
     * 内存足够时不会被回收，反之回收，适用于缓存而且不会OOM
     */
    public void softReference() {
        SoftReference<Object[]> reference = new SoftReference<>(new Object[300000000]);
        System.out.println(reference.get());
        Object[] objects = new Object[100000000];//3
        System.out.println(reference.get());

        //输出结果 Objectxxx,null
        //说明执行代码到强引用时，内存不够，垃圾回收器主动回收了软引用指向的对象

        //PS Object 数组的长度根据JVM配置不同而不同
    }

    /**
     * 弱引用
     * 垃圾回收器扫描到弱引用指向的对象时，才会回收。
     * 声明周期比软引用还要短。ThreadLocal的key使用了弱引用
     */
    private void weakReference() {
        WeakReference<String> reference = new WeakReference<>(new String("Hello"));
        System.out.println(reference.get());
        System.gc();//垃圾回收
        System.out.println(reference.get());
        //输出结果 hello   null
    }


    /**
     * 虚引用
     * 任何时候都可能被回收，必须和引用队列关联使用
     */
    private void xuReference() {
        ReferenceQueue<String> queue = new ReferenceQueue<>();
        PhantomReference<String> reference = new PhantomReference<>(new String("hello"), queue);
        System.out.println(reference.get());
        //输出结果 null
    }


}
