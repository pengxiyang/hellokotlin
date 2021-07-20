package com.test.hellokotlin.retrofit

import com.test.hellokotlin.bean.Person
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("test.json")
    fun  getPersonData():Call<List<Person>>
}