package com.example.sopt_27_android.data

data class ResponsePractice(
    val data : List<PracticeData>
)

data class PracticeData(
    val email: String,
    val first_name : String,
    val avatar : String
)