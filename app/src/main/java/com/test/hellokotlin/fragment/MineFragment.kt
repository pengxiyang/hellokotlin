package com.test.hellokotlin.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.permissionx.guolindev.PermissionX
import com.test.hellokotlin.R
import com.test.hellokotlin.databinding.FragmentMineBinding
import java.util.jar.Manifest

class MineFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentMineBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMineBinding.inflate(inflater, container, false)
        initData()
        return binding.root
    }

    private fun initData() {
        binding.cameraBt.setOnClickListener(this)
        binding.photoBt.setOnClickListener(this)
        binding.callBt.setOnClickListener(this)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.camera_bt -> {
            }
            R.id.photo_bt -> {
            }
            R.id.call_bt -> {
                PermissionX.init(this)
                    .permissions(android.Manifest.permission.CALL_PHONE)
                    .onExplainRequestReason{ scope, deniedList ->


                    }
                    .request{ allGranted, grantedList, deniedList ->
                        if(allGranted){
                            call()
                        }

                    }

            }
        }
    }

    /**
     * 打电话
     */
    fun call() {

        // val intent =Intent(Intent.ACTION_DIAL)
        val intent =Intent(Intent.ACTION_CALL)
        intent.data =Uri.parse("tel:18538190564")
        startActivity(intent)


    }
}