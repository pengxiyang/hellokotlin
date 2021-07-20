package com.test.hellokotlin.activity.net

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.hellokotlin.R
import com.test.hellokotlin.bean.Person
import com.test.hellokotlin.databinding.ActivityNetBinding
import com.test.hellokotlin.http.HttpCallbackListener
import com.test.hellokotlin.http.HttpUtil
import com.test.hellokotlin.retrofit.ApiService
import com.test.hellokotlin.retrofit.ServiceCreator
import okhttp3.*
import org.json.JSONArray
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class NetActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityNetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bt.setOnClickListener(this)
        binding.bt2.setOnClickListener(this)
        binding.bt3.setOnClickListener(this)
        binding.bt4.setOnClickListener(this)
    }


    private fun init() {
        thread {
            var connection: HttpURLConnection? = null
            try {
                val url = URL("https://www.baidu.com")
                connection = url.openConnection() as HttpURLConnection
                //设置请求所使用的方法
                connection.requestMethod = "GET"
                //设置连接超时时间
                connection.connectTimeout = 8000
                //设置读取超时时间
                connection.readTimeout = 8000
                //获取服务器返回的输入流
                val input = connection.inputStream
                val response = StringBuilder()
                //对获取到的输入流进行读取
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                showResponse(response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection?.disconnect()
            }
        }
    }

    fun  sendHttpRequest(){
        val  url ="http://192.168.1.205:88/test.json"
        HttpUtil.sendHttpRequest(url,object :HttpCallbackListener{
            override fun onFinish(response: String) {

            }

            override fun onError(exception: Exception) {

            }

        })
    }

    fun sendOkHttpRequest(){
        val  url ="http://192.168.1.205:88/test.json"
        HttpUtil.sendOkHttpRequest(url,object :Callback{
            override fun onFailure(call: Call, e: IOException) {
                //都是子线程

            }

            override fun onResponse(call: Call, response: Response) {

            }

        })

    }

    fun  sendRetrofitRequest(){
        val  url ="http://192.168.1.205:88/"
        val  retofit =Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val  appService =retofit.create(ApiService::class.java)
        appService.getPersonData().enqueue(object :retrofit2.Callback<List<Person>>{
            override fun onResponse(
                call: retrofit2.Call<List<Person>>,
                response: retrofit2.Response<List<Person>>
            ) {
               val  list =response.body()
                if(list!=null){
                    for (app in list){

                    }
                }
            }

            override fun onFailure(call: retrofit2.Call<List<Person>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    fun  sendRetrofitRequest2(){
        val appService =ServiceCreator.create(ApiService::class.java)
        val  appService2 =ServiceCreator.create<ApiService>()
    }

    fun sendRequestOkhttp3() {
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("http://192.168.1.205:88/test.json")
                    .build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if (responseData != null) {
                    parseJSONWithJSONObject(responseData)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun sendRequestGson() {
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("http://192.168.1.205:88/test.json")
                    .build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if (responseData != null) {
                    parseJSONWithGson(responseData)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun parseJSONWithJSONObject(jsonData: String) {
        val sb = StringBuffer()
        try {
            val jsonArray = JSONArray(jsonData)
            for (i in 0 until jsonArray.length()) {
                val jsonObj = jsonArray.getJSONObject(i)
                val id = jsonObj.getString("id")
                val version = jsonObj.getString("version")
                val name = jsonObj.getString("name")
                sb.append("id is $id,name is $name,version is $version \n")
                showResponse(sb.toString())
            }
        } catch (e: Exception) {

        }

    }

    private fun  parseJSONWithGson(jsonData: String){
        val  gson = Gson()
        val  sb =StringBuffer()
        val  typeOf = object : TypeToken<List<Person>>() {}.type
        val  personList =gson.fromJson<List<Person>>(jsonData,typeOf)
        for (person in personList){
            sb.append("id is ${person.id},name is ${person.name},version is ${person.version} \n")
        }
        showResponse2(sb.toString())

    }

    private fun showResponse(response: String) {
        runOnUiThread {
            binding.tv.text = response
        }

    }
    private fun showResponse2(response: String) {
        runOnUiThread {
            binding.tv1.text = response
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bt -> init()
            R.id.bt2 -> sendRequestOkhttp3()
            R.id.bt3 ->{
                binding.tv.text = ""
                binding.tv1.text=""
            }
            R.id.bt4 -> sendRequestGson()

        }
    }
}