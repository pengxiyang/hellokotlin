package com.test.hellokotlin.retrofit

import com.test.hellokotlin.bean.Data
import okhttp3.CacheControl
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ExampleService {

    @GET("get_data.json")
    fun getData(): Call<Data>

    //GET http://example.com/<page>/get_data.json
    @GET("{page}/get_data.json")
    fun getData(@Path("page") page: Int): Call<Data>
    //GET http://example.com/get_data.json?u=<user>&t=<token>

    @GET("get_data.json")
    fun getData(@Query("u") user: String, @Query("t") token: String): Call<Data>
    //DELETE http://example.com/data/<id>
    @DELETE("data/{id}")
    fun  deleteData(@Path("id") id:String):Call<ResponseBody>
    //POST http://example.com/data/create
    //{"id": 1, "content": "The description for this data."}
    @POST("data/create")
    fun  createData(@Body data: Data):Call<ResponseBody>

    //GET http://example.com/get_data.json
    //User-Agent: okhttp
    //Cache-Control: max-age=0

    @Headers("User-Agent：okhttp","Cache-Control:max-age=0")
    @GET("get_data.json")
    fun  getData1():Call<Data>
    //动态指定header的值

    @GET("get_data.json")
    fun getData2(@Header("User-Agent") userAgent:String,@Header("Cache-Control") cacheControl:String):Call<Data>



}