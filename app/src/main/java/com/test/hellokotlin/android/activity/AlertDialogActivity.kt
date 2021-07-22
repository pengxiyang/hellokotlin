package com.test.hellokotlin.android.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.test.hellokotlin.databinding.ActivityAlertDialogBinding

/**
 *  created by pxy on 2021/4/23
 *
 */
class AlertDialogActivity :AppCompatActivity() {
    private lateinit var binding: ActivityAlertDialogBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlertDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bt.setOnClickListener {
            alertDialog()
        }
    }

    fun alertDialog(){
        AlertDialog.Builder(this).apply {
            setTitle("提示")
            setMessage("Something")
            setCancelable(false)
            setPositiveButton("确定"){
                dialog, which ->
            }
            setNegativeButton("取消"){
                dialog, which ->
            }
            show()
        }



    }
}