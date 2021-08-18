package com.test.hellokotlin.android.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.test.hellokotlin.*
import com.test.hellokotlin.android.activity.HandlerActivity
import com.test.hellokotlin.android.activity.MaterialDesignActivity
import com.test.hellokotlin.android.activity.TestActivity
import com.test.hellokotlin.android.activity.file.FileActivity
import com.test.hellokotlin.android.activity.jetpack.LiveDataActivity
import com.test.hellokotlin.android.activity.jetpack.ViewModel2Activity
import com.test.hellokotlin.android.activity.jetpack.ViewModelActivity
import com.test.hellokotlin.android.activity.net.NetActivity
import com.test.hellokotlin.android.activity.web.WebActivity
import com.test.hellokotlin.databinding.FragmentHomeBinding
import com.test.hellokotlin.android.utils.StartActivityUtils

class HomeFragment : Fragment() ,View.OnClickListener{
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
        binding.apply {
            toMainBt.setOnClickListener(this@HomeFragment)
            viewModelBt.setOnClickListener(this@HomeFragment)
            viewModel2Bt.setOnClickListener(this@HomeFragment)
            liveDataBt.setOnClickListener(this@HomeFragment)
            handlerBt.setOnClickListener(this@HomeFragment)
            materialBt.setOnClickListener(this@HomeFragment)
            material2Bt.setOnClickListener(this@HomeFragment)
            httpBt.setOnClickListener(this@HomeFragment)
            testBt.setOnClickListener(this@HomeFragment)
            fileBt.setOnClickListener(this@HomeFragment)
        }
//
//        binding.toMainBt.setOnClickListener {
//            StartActivityUtils.jump2MainActivity(binding.root.context)
////         StartActivityUtils.jump2MainActivity(context)
////         jumpToMainActivity(context)
//
//        }

    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.handler_bt->{
                jumpToActivity(context,HandlerActivity::class.java)
            }
            R.id.material_bt->{
                jumpToActivity(context,MaterialDesignActivity::class.java)
            }
            R.id.to_main_bt-> StartActivityUtils.jump2MainActivity(binding.root.context)
            R.id.view_model_bt-> jumpToActivity(context,ViewModelActivity::class.java)
            R.id.view_model2_bt-> jumpToActivity(context,ViewModel2Activity::class.java)
            R.id.live_data_bt-> jumpToActivity(context,LiveDataActivity::class.java)
            R.id.material2_bt-> {
                jumpToActivity(context,WebActivity::class.java)
            }
            R.id.http_bt-> jumpToActivity(context,NetActivity::class.java)
            R.id.test_bt-> jumpToActivity(context,TestActivity::class.java)

            R.id.file_bt-> jumpToActivity(context,FileActivity::class.java)

        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}