package com.test.hellokotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.test.hellokotlin.databinding.FragmentHomeBinding
import com.test.hellokotlin.jumpToMainActivity
import com.test.hellokotlin.jumpToViewModel2Activity
import com.test.hellokotlin.jumpToViewModelActivity
import com.test.hellokotlin.utils.StartActivityUtils

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        initData()
        return binding.root
    }

    private fun initData() {
        binding.toMainBt.setOnClickListener {
            StartActivityUtils.jump2MainActivity(binding.root.context)
//         StartActivityUtils.jump2MainActivity(context)
//         jumpToMainActivity(context)

        }
        binding.viewModelBt.setOnClickListener {
            jumpToViewModelActivity(context)
        }
        binding.viewModel2Bt.setOnClickListener {
            jumpToViewModel2Activity(context)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}