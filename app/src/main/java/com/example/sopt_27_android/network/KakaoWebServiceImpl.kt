package com.example.sopt_27_android.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KakaoWebServiceImpl {
    var retrofit = Retrofit.Builder()
        .baseUrl("https://dapi.kakao.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val webservice : KakaoWebInterface = retrofit.create(KakaoWebInterface::class.java)
}