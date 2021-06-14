package com.test.hellokotlin

import android.content.Context
import android.content.Intent
import com.test.hellokotlin.activity.SecondActivity

/**
 *  created by pxy on 2021/4/23
 *
 */
class StartActivityUtils {

    companion object{
        fun actionStart(cotext: Context, data1:String, data2: String){
            val  intent = Intent(cotext, SecondActivity::class.java)
            intent.putExtra("param1",data1)
            intent.putExtra("param2",data2)
            cotext.startActivity(intent)
        }

        fun test(){

        }
    }
}