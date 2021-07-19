package com.test.hellokotlin.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar


fun View.showSnackbarNew(text:String,actionText:String? = null,duration:Int=Snackbar.LENGTH_SHORT,block: (()->Unit)? = null){
    val  snackbar =Snackbar.make(this,text,duration)
    if(actionText!=null &&block !=null){
        snackbar.setAction(actionText){
            block
        }
    }
    snackbar.show()
}