package com.test.hellokotlin.android.fragment

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
import com.test.hellokotlin.jumpToCameraActivity
import com.test.hellokotlin.jumpToMediaPlayerActivity
import com.test.hellokotlin.jumpToVideoPlayerActivity


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
        binding.dialBt.setOnClickListener(this)
        binding.callBt.setOnClickListener(this)
        binding.mediaPlayerBt.setOnClickListener(this)
        binding.videoPlayerBt.setOnClickListener(this)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.camera_bt -> jumpToCameraActivity(context)
            R.id.dial_bt -> call(1)
            R.id.call_bt -> requestCallPermission()
            R.id.media_player_bt -> jumpToMediaPlayerActivity(context)
            R.id.video_player_bt-> jumpToVideoPlayerActivity(context)
        }
    }

    /**
     * 请求权限
     */
    fun requestCallPermission() {
        PermissionX.init(this)
            .permissions(android.Manifest.permission.CALL_PHONE)
            .onExplainRequestReason { scope, deniedList ->
                scope.showRequestReasonDialog(deniedList, "PermissionX需要您同意以下权限才能正常使用", "同意", "拒绝")
            }
            .onForwardToSettings { scope, deniedList ->
                scope.showForwardToSettingsDialog(deniedList, "必要权限请授权，否则无法使用该功能", "同意", "拒绝")
            }
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    call(2)
                } else {

                }
            }
    }

    /**
     * 打电话
     */
    fun call(type: Int) {
        val intent: Intent
        if (type == 1) {
            intent = Intent(Intent.ACTION_DIAL)
        } else {
            intent = Intent(Intent.ACTION_CALL)
        }

        intent.data = Uri.parse("tel:18538190564")
        startActivity(intent)


    }
}