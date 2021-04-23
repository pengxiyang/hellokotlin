package com.test.hellokotlin

import android.app.Activity

/**
 *  created by pxy on 2021/4/22
 *
 */
object ActivityCollector  {
    private val activitys =ArrayList<Activity>()

    fun addActivity(activity: Activity){
        activitys.add(activity)
    }

    fun  removeActivity(activity: Activity){
        activitys.remove(activity)
    }

    fun   finishAll(){
        for (activity in activitys){
            if(!activity.isFinishing){
                activity.finish()
            }
        }
        activitys.clear()
    }
}