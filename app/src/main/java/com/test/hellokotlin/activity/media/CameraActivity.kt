package com.test.hellokotlin.activity.media

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.permissionx.guolindev.PermissionX
import com.test.hellokotlin.R
import com.test.hellokotlin.databinding.ActivityCameraBinding
import java.io.File

class CameraActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityCameraBinding
    private val takePhoto = 1
    private val fromAlbum = 2
    private lateinit var imageUri: Uri
    private lateinit var outputImage: File


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()

    }

    private fun initData() {
        binding.cameraBt.setOnClickListener(this)
        binding.albumBt.setOnClickListener(this)


    }


    fun requestCameraPermission() {
        PermissionX.init(this)
            .permissions(android.Manifest.permission.CAMERA)
            .onExplainRequestReason { scope, deniedList ->
                scope.showRequestReasonDialog(deniedList, "PermissionX需要您同意以下权限才能正常使用", "同意", "拒绝")
            }
            .onForwardToSettings { scope, deniedList ->
                scope.showForwardToSettingsDialog(
                    deniedList,
                    "必要权限请授权，否则无法使用该功能",
                    "同意",
                    "拒绝"
                )
            }
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    takePhoto()
                }
            }
    }

    /**
     * 拍照
     */
    fun takePhoto() {
        //创建File对象，用于存储拍照后的照片
        outputImage = File(externalCacheDir, "output_image.jpg")
        if (outputImage.exists()) {
            outputImage.delete()
        }
        outputImage.createNewFile()
        imageUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            FileProvider.getUriForFile(this, "com.test.hellokotlin.fileprovider", outputImage)
        } else {
            Uri.fromFile(outputImage)
        }
        //启动相机程序
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(intent, takePhoto)

    }

    /**
     * 相册
     */
    fun fromAlbum() {
        val intent = Intent(
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) Intent.ACTION_GET_CONTENT else Intent.ACTION_OPEN_DOCUMENT
        )
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        startActivityForResult(intent, fromAlbum)
    }

    fun  fromAlbum1(){
        val intent =Intent(Intent.ACTION_PICK)
        intent.data =MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        intent.type ="image/*"
        startActivityForResult(intent,fromAlbum)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            takePhoto -> {
                if (resultCode == Activity.RESULT_OK) {
                    //将拍摄的照片展示出来
                    val bitmap =
                        BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri))
                    binding.imageIv.setImageBitmap(rotateIfRequired(bitmap))
                }

            }
            fromAlbum -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    data.data?.let { uri ->
                        val bitmap = getBitmapFromUri(uri)
                        binding.imageIv.setImageBitmap(bitmap)

                    }
                }
            }
        }
    }

    private fun rotateIfRequired(bitmap: Bitmap): Bitmap {
        val exif = ExifInterface(outputImage.path)
        val orientation =
            exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)

        return when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateBitmap(bitmap, 90)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateBitmap(bitmap, 180)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateBitmap(bitmap, 270)
            else -> bitmap

        }


    }

    private fun rotateBitmap(bitmap: Bitmap, degree: Int): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        val rotatedBitmap =
            Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        bitmap.recycle()//将不需要的bitmap对象回收
        return rotatedBitmap
    }

    private fun getBitmapFromUri(uri: Uri) = contentResolver.openFileDescriptor(uri, "r")
        ?.use {
            BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
        }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.camera_bt -> requestCameraPermission()
            R.id.album_bt -> fromAlbum1()

        }
    }
}