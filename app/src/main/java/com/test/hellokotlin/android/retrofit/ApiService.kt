package com.test.hellokotlin.android.retrofit

import com.test.hellokotlin.android.bean.Person
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("test.json")
    fun  getPersonData():Call<List<Person>>
}