package com.test.hellokotlin

import android.content.Context
import android.content.Intent
import com.test.hellokotlin.activity.*


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
   context.startActivity(Intent(context,ListViewActivity::class.java))
}

fun  jump2MapTabActivity(context: Context){
    context.startActivity(Intent(context,MapTabActivity::class.java))
}

fun jump2MapActivity(context: Context){
    context.startActivity(Intent(context,MapActivity::class.java))

}

fun  jump2RecyclerViewActivity(context: Context){
    context.startActivity(Intent(context,RecyclerViewActivity::class.java))


}
fun  jump2ChatActivity(context: Context){
    context.startActivity(Intent(context,ChatActivity::class.java))
}
fun  jump2TestFragmentActivity(context: Context){
    context.startActivity(Intent(context,TestFragmentActivity::class.java))
}

fun jumpToRunningActivity(context: Context){
    context.startActivity(Intent(context,RunningActivity::class.java))

}
