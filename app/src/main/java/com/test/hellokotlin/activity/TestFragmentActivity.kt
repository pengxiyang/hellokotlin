package com.test.hellokotlin.activity

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.test.hellokotlin.R
import com.test.hellokotlin.databinding.ActivityTestFragmentBinding
import com.test.hellokotlin.fragment.AnotherRightFragment
import com.test.hellokotlin.fragment.RightFragment

/**
 *  created by pxy on 2021/4/27
 *
 */
class TestFragmentActivity:AppCompatActivity() {
    private lateinit var binding: ActivityTestFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bt: Button =findViewById(R.id.bt_f)
        bt.setOnClickListener {
          //  Toast.makeText(this,"123",Toast.LENGTH_SHORT).show()
            replaceFragment(AnotherRightFragment())
        }

        replaceFragment(RightFragment())

    }

    private fun  replaceFragment(fragment:Fragment){
        val  fragmentManager =supportFragmentManager
        val  transcation =fragmentManager.beginTransaction()
        transcation.replace(R.id.right_layout,fragment)
        transcation.commit()

    }
}