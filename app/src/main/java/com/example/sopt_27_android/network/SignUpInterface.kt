package com.example.sopt_27_android.network

import com.example.sopt_27_android.data.RequestSignUp
import com.example.sopt_27_android.data.ResponseSignUp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignUpInterface {
    @Headers("Content-Type:application/json")
    @POST("/users/signup")
    fun requestSignUp(@Body body: RequestSignUp): Call<ResponseSignUp>
}