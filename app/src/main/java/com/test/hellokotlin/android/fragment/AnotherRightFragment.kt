package com.test.hellokotlin.android.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.test.hellokotlin.databinding.AnotherRightFragmentBinding

/**
 *  created by pxy on 2021/4/27
 *
 */
class AnotherRightFragment:Fragment() {
    private var _binding:AnotherRightFragmentBinding?=null
    private val  binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = AnotherRightFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding =null

    }
}