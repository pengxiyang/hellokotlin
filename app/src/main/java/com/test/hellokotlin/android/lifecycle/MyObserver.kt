package com.test.hellokotlin.android.lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * lifecycle
 *
 * lifecycle.currentState返回的生命周期状态是一个枚举类型，
一共有INITIALIZED、DESTROYED、CREATED、STARTED、RESUMED
，当获取的生命周期状态是CREATED的时候，说明onCreate()方法已经执行了，但
是onStart()方法还没有执行。当获取的生命周期状态是STARTED的时候，说明onStart()
方法已经执行了，但是onResume()方法还没有执行，以此类推

 */

class MyObserver(val lifecycle: Lifecycle) : LifecycleObserver {
    companion object {
        val TAG = MyObserver::class.java.simpleName
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun activityStart() {
        Log.d(TAG, "activityStart: " + lifecycle.currentState)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun activityStop() {
        Log.d(TAG, "activityStop: ")

    }

}