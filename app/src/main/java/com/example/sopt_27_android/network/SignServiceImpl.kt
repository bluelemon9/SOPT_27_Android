package com.example.sopt_27_android.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SignServiceImpl {
    private const val BASE_URL = "http://15.164.83.210:3000/"
    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val signinservice : SignInInterface = retrofit.create(SignInInterface::class.java)
    val signupservice : SignUpInterface = retrofit.create(SignUpInterface::class.java)
}