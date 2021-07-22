package com.test.hellokotlin.android.utils

import android.content.Context
import android.graphics.Bitmap
import android.provider.MediaStore

class FileUtil {

    private fun saveImage(context: Context,imagePath:String,title:String,desc:String){
        MediaStore.Images.Media.insertImage(context.contentResolver,imagePath,title,desc)

    }
    private fun saveImage(context: Context,bitmap: Bitmap,title:String,des:String){
        MediaStore.Images.Media.insertImage(context.contentResolver,bitmap,title,des)

    }
}