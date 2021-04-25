package com.test.hellokotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.test.hellokotlin.activity.AlertDialogActivity
import com.test.hellokotlin.activity.ListActivity
import com.test.hellokotlin.activity.MapActivity
import com.test.hellokotlin.activity.MapTabActivity


/**
 *  created by pxy on 2021/4/23
 *  顶层方法
 *
 */

 fun  doSomething(){
    println("do soemthing")
}

fun  jump2AlertDialogActivity(context:Context){
    context.startActivity(Intent(context,AlertDialogActivity::class.java))

}

fun  jump2ListActivity(context: Context){
   context.startActivity(Intent(context,ListActivity::class.java))
}

fun  jump2MapTabActivity(context: Context){
    context.startActivity(Intent(context,MapTabActivity::class.java))
}

fun jump2MapActivity(context: Context){
    context.startActivity(Intent(context,MapActivity::class.java))

}
