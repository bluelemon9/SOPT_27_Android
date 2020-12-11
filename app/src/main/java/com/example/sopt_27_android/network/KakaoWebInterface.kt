package com.example.sopt_27_android.network

import com.example.sopt_27_android.data.ResponseWebData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KakaoWebInterface {
    @Headers("Authorization: KakaoAK 15ff8d75c808218fb179d2edc226cc27")
    @GET("/v2/search/web")

    fun requestWeb(@Query("query") searchContent : String) : Call<ResponseWebData>
}