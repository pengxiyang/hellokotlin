package com.test.hellokotlin.utils

import android.content.Context
import android.content.Intent
import com.test.hellokotlin.MainActivity

object StartActivityUtils {
    fun  jump2MainActivity(context: Context?){
        val  intent = Intent(context,MainActivity::class.java)
        context?.startActivity(intent)
    }
}