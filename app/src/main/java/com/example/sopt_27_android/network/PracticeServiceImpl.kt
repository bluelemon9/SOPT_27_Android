package com.example.sopt_27_android.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PracticeServiceImpl {
    private const val BASE_URL = "https://reqres.in"
    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service : PracticeInterface = retrofit.create(PracticeInterface::class.java)
}