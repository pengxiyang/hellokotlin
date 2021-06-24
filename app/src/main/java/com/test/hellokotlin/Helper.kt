package com.test.hellokotlin

import android.content.Context
import android.content.Intent
import com.test.hellokotlin.activity.*
import com.test.hellokotlin.activity.jetpack.ViewModel2Activity
import com.test.hellokotlin.activity.jetpack.ViewModelActivity
import com.test.hellokotlin.activity.list.ChatActivity
import com.test.hellokotlin.activity.list.ListViewActivity
import com.test.hellokotlin.activity.list.RecyclerViewActivity
import com.test.hellokotlin.activity.map.MapActivity
import com.test.hellokotlin.activity.map.MapTabActivity
import com.test.hellokotlin.activity.media.CameraActivity
import com.test.hellokotlin.activity.media.MediaPlayerActivity
import com.test.hellokotlin.activity.media.VideoPlayerActivity
import com.test.hellokotlin.activity.permission.PermissionActivity

var ARCGIS_APPKEY = "AAPK678cc7aef3d34d09a44a390f17cdf3e7-6rAoyy0PGvNoNeffCJ2rmmjbbZSqAsgJbyAz6e_0nsh9zgzJzHdhi4AIPSHNXru"

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
   context.startActivity(Intent(context,
       ListViewActivity::class.java))
}

fun  jump2MapTabActivity(context: Context){
    context.startActivity(Intent(context,
        MapTabActivity::class.java))
}

fun jump2MapActivity(context: Context){
    context.startActivity(Intent(context,
        MapActivity::class.java))

}

fun  jump2RecyclerViewActivity(context: Context){
    context.startActivity(Intent(context,
        RecyclerViewActivity::class.java))


}
fun  jump2ChatActivity(context: Context){
    context.startActivity(Intent(context,
        ChatActivity::class.java))
}
fun  jump2TestFragmentActivity(context: Context){
    context.startActivity(Intent(context,TestFragmentActivity::class.java))
}

fun jumpToRunningActivity(context: Context){
    context.startActivity(Intent(context,RunningActivity::class.java))
}

fun jumpToMainActivity(context: Context?){
    context?.startActivity(Intent(context,MainActivity::class.java))
}
fun jumpToCameraActivity(context: Context?){
    context?.startActivity(Intent(context,
        CameraActivity::class.java))
}
fun jumpToMediaPlayerActivity(context: Context?){
    context?.startActivity(Intent(context,
        MediaPlayerActivity::class.java))
}
fun jumpToVideoPlayerActivity(context: Context?){
    context?.startActivity(Intent(context,
        VideoPlayerActivity::class.java))
}

fun  jumpToPermissionActivity(context: Context?){
    context?.startActivity(Intent(context,PermissionActivity::class.java))
}

fun  jumpToViewModelActivity(context: Context?){
    context?.startActivity(Intent(context,ViewModelActivity::class.java))
}
fun  jumpToViewModel2Activity(context: Context?){
    context?.startActivity(Intent(context, ViewModel2Activity::class.java))
}