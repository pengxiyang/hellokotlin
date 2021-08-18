package com.test.hellokotlin.android.activity.file

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.test.hellokotlin.R
import com.test.hellokotlin.android.utils.showToast
import com.test.hellokotlin.databinding.FileActivityBinding
import java.io.*
import java.lang.StringBuilder

class FileActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: FileActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FileActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            inputBt.setOnClickListener(this@FileActivity)
            loadBt.setOnClickListener(this@FileActivity)
            saveSpBt.setOnClickListener(this@FileActivity)
            restoreSpBt.setOnClickListener(this@FileActivity)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.input_bt -> {
                var  text =binding.inputEt.text.toString()
                save(text)
            }

            R.id.load_bt->{
                val  text =load()
               if(text.isNotEmpty()){
                   binding.inputEt.setText(text)
                   binding.inputEt.setSelection(text.length)

                   "Restoring succeeded".showToast(this)
               }
            }
            R.id.save_sp_bt->{
                val  editor =getSharedPreferences("sp",Context.MODE_PRIVATE).edit()
                editor.putString("name","Tim")
                editor.putInt("age",28)
                editor.putBoolean("married",false)
                editor.apply()
                "Save Success".showToast(this)
            }
            R.id.restore_sp_bt->{
                val  prefs =getSharedPreferences("sp",Context.MODE_PRIVATE)
                val name =prefs.getString("name","")
                val age =prefs.getInt("age",0)
                val married =prefs.getBoolean("married",false)
                val  text ="name is $name,age is$age,married is $married"
                binding.spTv.text =text
            }

        }
    }

    fun save(inputText: String) {
        if(inputText.isEmpty()){
            return
        }
        try {
            val output = openFileOutput("data", Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(output))
            writer.use {
                it.write(inputText)
            }
            "Data saved".showToast(this)

        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun load():String{
        val  content =StringBuilder()
        try {
            val  input =openFileInput("data")
            val  reader =BufferedReader(InputStreamReader(input))

            reader.use {
                reader.forEachLine {
                    content.append(it)
                }
            }
        }catch (e:IOException){
            e.printStackTrace()

        }

        return  content.toString()

    }


}