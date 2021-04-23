package com.test.hellokotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.test.hellokotlin.databinding.FragmentTestBinding

/**
 *  created by pxy on 2021/4/22
 *
 */
class TestFragment : Fragment() {
    private var  binding :FragmentTestBinding? =null
    private val mBinding get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTestBinding.inflate(inflater,container,false)
        return mBinding.root
    }

}