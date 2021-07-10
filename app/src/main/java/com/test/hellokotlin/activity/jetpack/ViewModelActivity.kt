package com.test.hellokotlin.activity.jetpack

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import com.test.hellokotlin.R
import com.test.hellokotlin.databinding.ActivityViewModelBinding
import com.test.hellokotlin.lifecycle.MyObserver
import com.test.hellokotlin.viewmodel.MainViewModel
import com.test.hellokotlin.factory.MainViewModelFactory

class ViewModelActivity :AppCompatActivity(),View.OnClickListener{

    private lateinit var  binding :ActivityViewModelBinding
    lateinit var viewModel: MainViewModel
    lateinit var sp:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewModelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycle.addObserver(MyObserver(lifecycle))
        init()
    }

    private fun init() {
        sp=getPreferences(Context.MODE_PRIVATE)
       // viewModel =ViewModelProvider(this).get(MainViewModel::class.java)
        val countReserved =sp.getInt("count_reserved",0)
        viewModel =ViewModelProvider(this, MainViewModelFactory(countReserved)).get(MainViewModel::class.java)
        binding.bt.setOnClickListener {
            viewModel.counter++
            refreshCounter()

        }
        binding.bt1.setOnClickListener(this)

        refreshCounter()
    }
    private fun  refreshCounter(){
        binding.tv.text =viewModel.counter.toString()

    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved",viewModel.counter)
        }
    }

    override fun onClick(v: View?) {
       when(v?.id){
           R.id.bt1->{
               viewModel.counter=0
               refreshCounter()

           }

       }
    }

}