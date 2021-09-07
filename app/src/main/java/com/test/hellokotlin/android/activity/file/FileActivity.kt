package com.test.hellokotlin.android.activity.file

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.hellokotlin.R
import com.test.hellokotlin.android.db.MyDatabaseHelper
import com.test.hellokotlin.android.utils.showToast
import com.test.hellokotlin.databinding.FileActivityBinding
import java.io.*
import java.lang.StringBuilder

class FileActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: FileActivityBinding
    private var  dbHelper:MyDatabaseHelper? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FileActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbHelper = MyDatabaseHelper(this,"BookStore.db",2)
        binding.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            inputBt.setOnClickListener(this@FileActivity)
            loadBt.setOnClickListener(this@FileActivity)
            saveSpBt.setOnClickListener(this@FileActivity)
            restoreSpBt.setOnClickListener(this@FileActivity)
            createDbBt.setOnClickListener(this@FileActivity)
            addBt.setOnClickListener(this@FileActivity)
            modifyBt.setOnClickListener(this@FileActivity)
            deleteBt.setOnClickListener(this@FileActivity)
            queryBt.setOnClickListener(this@FileActivity)
            query2Bt.setOnClickListener(this@FileActivity)
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
            R.id.create_db_bt->{
                dbHelper?.writableDatabase
            }
            R.id.add_bt -> {
                val db = dbHelper?.writableDatabase
//                val  values =ContentValues().apply {
//                    put("name","The Da Vinci Code")
//                    put("author","Dan Brown")
//                    put("pages",560)
//                    put("price",16.96)
//                }
//                var tag=db?.insert("Book",null,values)
//             if(tag==-1L)  "插入失败".showToast(this@FileActivity) else "插入成功".showToast(this)
                db?.execSQL(
                    "insert into Book (name,author,pages,price) values(?,?,?,?)",
                    arrayOf("Dear Jhon", "Belly", "560", "16.79")
                )
                db?.execSQL("insert into Book (name,author,pages,price) values(?,?,?,?)", arrayOf("Leaves","Tayler","456","20"))

            }
            R.id.modify_bt -> {
                val db = dbHelper?.writableDatabase
//                val values = ContentValues()
//                values.put("price", 10.99)
//                db?.update("Book", values, "price =?", arrayOf("16.96"))

                db?.execSQL("update Book set price = ? where name = ?", arrayOf("19.99", "The Da Vinci Code"))
                db?.execSQL("update Book set price = ? where name = ?", arrayOf("20.12","Leaves"))

            }
            R.id.delete_bt -> {
                val db = dbHelper?.writableDatabase
//                var tag = db?.delete("Book", "pages > ?", arrayOf("500"))
//
//                if(tag===0) "暂无合适数据删除".showToast(this) else "删除成功".showToast(this)
                db?.execSQL("delete from Book where pages > ?", arrayOf("500"))

            }
            R.id.query_bt -> {
                val db = dbHelper?.writableDatabase
                val cursor = db!!.query("Book", null, null, null, null, null, null)
                if (cursor.moveToFirst()) {
                    do {
                        //遍历cursor对象，取出数据并打印
                        val name = cursor.getString(cursor.getColumnIndex("name"))
                        val author = cursor.getString(cursor.getColumnIndex("author"))
                        val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                        val price = cursor.getDouble(cursor.getColumnIndex("price"))
                        Log.d("----FileActivity", "book name is $name")
                        Log.d("----FileActivity", "book author is $author")
                        Log.d("----FileActivity", "book pages is $pages")
                        Log.d("----FileActivity", "book price is $price")

                    } while (cursor.moveToNext())
                }
                cursor.close()
            }
            R.id.query2_bt->{
                val db =dbHelper?.writableDatabase
                val  cursor  =db?.rawQuery("select * from Book",null)
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