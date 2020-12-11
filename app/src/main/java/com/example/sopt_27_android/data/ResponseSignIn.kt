package com.example.sopt_27_android.data

data class ResponseSignIn(
    val status: Int,
    val success : Boolean,
    val message : String,
    val data: SomeData
)

data class SomeData(
    val email: String,
    val password: String,
    val userName: String
)