package com.example.sopt_27_android.network

import com.example.sopt_27_android.data.RequestSignIn
import com.example.sopt_27_android.data.ResponseSignIn
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignInInterface {
    @Headers("Content-Type:application/json")
    @POST("/users/signin")
    fun requestSignIn(@Body body: RequestSignIn): Call<ResponseSignIn>
}