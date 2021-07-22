package com.test.hellokotlin.android.activity.permission

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.permissionx.guolindev.PermissionX
import com.test.hellokotlin.databinding.ActivityRequestPermissionBinding

class PermissionActivity : AppCompatActivity() {

    private val binding: ActivityRequestPermissionBinding by lazy {
        ActivityRequestPermissionBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.storageBt.setOnClickListener {
            requestStoragePermission()
        }
        binding.locationBt.setOnClickListener {
            PermissionX.init(this)
                .permissions(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION
                )
                .onExplainRequestReason { scope, deniedList, beforeRequest ->
                    scope.showRequestReasonDialog(deniedList, "需要定位权限", "同意", "拒绝")
                }
                .onForwardToSettings { scope, deniedList ->
                    scope.showForwardToSettingsDialog(deniedList, "必要权限请同意", "同意", "拒绝")
                }
                .request { allGranted, grantedList, deniedList ->
                    if (allGranted) {
                        Toast.makeText(this, "123", Toast.LENGTH_SHORT).show()
                    }

                }

        }
    }

    private fun requestStoragePermission() {
        PermissionX.init(this)
            .permissions(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.MANAGE_EXTERNAL_STORAGE
            )
            .onExplainRequestReason { scope, deniedList ->
                scope.showRequestReasonDialog(deniedList, "需要您同意以下权限", "同意", "拒绝")
            }
            .request { allGranted, grantedList, deniedList ->
            }
    }

    private fun requestLocation() {
        PermissionX.init(this)
            .permissions(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )
            .onExplainRequestReason { scope, deniedList, beforeRequest ->
                scope.showRequestReasonDialog(deniedList, "需要定位权限", "同意", "拒绝")
            }
            .onForwardToSettings { scope, deniedList ->
                scope.showForwardToSettingsDialog(deniedList, "必要权限请同意", "同意", "拒绝")
            }
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    Toast.makeText(this, "123", Toast.LENGTH_SHORT).show()
                }

            }

    }

}