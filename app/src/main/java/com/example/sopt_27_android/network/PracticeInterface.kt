package com.example.sopt_27_android.network

import com.example.sopt_27_android.data.RequestSignIn
import com.example.sopt_27_android.data.ResponsePractice
import com.example.sopt_27_android.data.ResponseSignIn
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface PracticeInterface {
    @GET("/api/users?page=2")
    fun requestPractice(): Call<ResponsePractice>
}