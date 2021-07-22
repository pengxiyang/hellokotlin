package com.test.hellokotlin.android.activity.media

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.test.hellokotlin.databinding.ActivityFileBinding
import java.io.File
import java.io.FileOutputStream
import java.net.URL

class FileActivity :AppCompatActivity() {
    private lateinit var  binding:ActivityFileBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFileBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    /**
     * 保存一个文件 调用createFile会拉起下面界面，用户保存后会回调到onActivityResult中，并将uri传来，得到uri后就可以写入了。
     */
    private fun createFile(fileName:String?,mimType:String?){
        val intent =Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        if(!TextUtils.isEmpty(mimType)){
            intent.type =mimType
        }
        intent.putExtra(Intent.EXTRA_TITLE,fileName)
        startActivityForResult(intent,1000)

    }

    /**
     * 读取一个文件，以读取图片为例
     */
    private fun openImage(){
        val  intent =Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type ="image/*"
        startActivityForResult(intent,1100)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            1000->{
                if (resultCode == Activity.RESULT_OK) {
                    var uri: Uri? = null
                    if (data != null) {
                        uri = data.data
                        val takeFlags = (intent.flags
                                and (Intent.FLAG_GRANT_READ_URI_PERMISSION
                                or Intent.FLAG_GRANT_WRITE_URI_PERMISSION))
                        // Check for the freshest data.
                        contentResolver.takePersistableUriPermission(uri!!, takeFlags)
                        val netUrl:String="http://xxx.png"
                        writeFile(uri, netUrl)
                    }
                }
            }
            1100->{
                if(resultCode== Activity.RESULT_OK){
                    var  uri:Uri?=null
                    if(data!=null){
                        uri=data.data
                        showImage(uri)
                    }

                }

            }
        }
    }

    private fun writeFile(uri: Uri,netUrl:String){
        Thread{
            val  pfd =contentResolver.openFileDescriptor(uri,"w")
            val  fileOutputStream =FileOutputStream(pfd!!.fileDescriptor)
            val buffer =ByteArray(1024)
            val  url =URL(netUrl);
            val  inputStream =url.openStream()
            while (true){
                val  numRead =inputStream.read(buffer)
                if(numRead==-1){
                    break
                }
                fileOutputStream.write(buffer,0,numRead)
                fileOutputStream.close()
                pfd.close()
                inputStream.close()

            }
        }.start()

    }
    private fun showImage(uri: Uri?){
        if(uri==null){
            return
        }
        val  pfd =contentResolver.openFileDescriptor(uri,"r")
        if(pfd!=null){
            val bitmap =BitmapFactory.decodeFileDescriptor(pfd.fileDescriptor)
            pfd.close()
            //将bitmap展示到界面
        }
    }

    /**
     * 删除文件
     */
    private fun  deleteImage(uri: Uri?){
        if(uri==null){
            return
        }
        val takeFlags =(intent.flags and (Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION))
        // // Check for the freshest data.
        contentResolver.takePersistableUriPermission(uri,takeFlags)

        DocumentsContract.deleteDocument(contentResolver,uri)
    }

    /**
     * 更新到图库
     */
    private fun  refreshFile(file:File){
        //方法1
        val  uri =Uri.fromFile(file)
        sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,uri))
        //方法2 也可以传入Bitmap
        MediaStore.Images.Media.insertImage(contentResolver,file.absolutePath,file.name,"")

    }



}