package com.test.hellokotlin.fragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.test.hellokotlin.databinding.FragmentMainBinding

class MainFragment:Fragment() {
    private var _binding: FragmentMainBinding?=null
    private val  binding get() = _binding!!
    lateinit var  timeChangeReceiver: TimeChangeReceiver

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        initData()
        return binding.root
    }

    private fun initData() {
        val  intentFilter =IntentFilter()
        intentFilter.addAction(Intent.ACTION_TIME_TICK)
        timeChangeReceiver =TimeChangeReceiver()
        context?.registerReceiver(timeChangeReceiver,intentFilter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding =null
        context?.unregisterReceiver(timeChangeReceiver)
    }

    inner  class  TimeChangeReceiver :BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
           Toast.makeText(context,"Time has change",Toast.LENGTH_SHORT).show()
        }

    }
}