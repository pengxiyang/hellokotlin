package com.test.hellokotlin.android.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.test.hellokotlin.R
import com.test.hellokotlin.databinding.ActivitySecondBinding
import com.test.hellokotlin.doSomething

/**
 *  created by pxy on 2021/4/22
 *
 */
class SecondActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG = "---SecondActivity"
    private lateinit var binding: ActivitySecondBinding
    companion object{
        fun actionStart(cotext: Context, data1:String, data2: String){
            val  intent = Intent(cotext,SecondActivity::class.java)
            intent.putExtra("param1",data1)
            intent.putExtra("param2",data2)
            cotext.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate: " )
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bt.setOnClickListener(this)
        binding.tv.setOnClickListener(this)
       /* binding.bt.setOnClickListener {

        }*/
        doSomething()
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.bt_jump->{

            }
            R.id.bt->{
              //  Toast.makeText(this,"1",Toast.LENGTH_SHORT).show()
                if(binding.progressCircular.visibility == View.VISIBLE){
                    binding.progressCircular.visibility =View.GONE

                }else{
                    binding.progressCircular.visibility =View.VISIBLE
                }
            }
            R.id.tv->{
               binding.progressHorizontal.progress=binding.progressHorizontal.progress+10
            }

        }

    }



    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart: " )
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(TAG, "onRestart: " )

    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume: " )
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause: " )
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop: " )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: " )
    }

}