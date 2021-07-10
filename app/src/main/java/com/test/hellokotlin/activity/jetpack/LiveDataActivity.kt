package com.test.hellokotlin.activity.jetpack

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.test.hellokotlin.R
import com.test.hellokotlin.databinding.ActivityViewModelBinding
import com.test.hellokotlin.viewmodel.LiveDataModel
import com.test.hellokotlin.factory.LiveDataModelFactory


class LiveDataActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityViewModelBinding
    lateinit var viewModel: LiveDataModel
    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewModelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved", 0)
        viewModel = ViewModelProvider(
            this,
            LiveDataModelFactory(countReserved)
        ).get(LiveDataModel::class.java)
        binding.bt.setOnClickListener {
            viewModel.plusOne()
        }
        binding.bt1.setOnClickListener(this)
//        viewModel.counter.observe(this, Observer { count->
//            binding.tv.text =count.toString()
//        })
        viewModel.counter.observe(this){count->
            binding.tv.text =count.toString()

        }


    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved", viewModel.counter.value ?: 0)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bt1 -> {
                viewModel.clear()
            }

        }
    }

}