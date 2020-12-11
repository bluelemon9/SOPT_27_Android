package com.example.sopt_27_android.data

data class ResponseWebData(
    val metas : List<MetaData>,
    val documents : List<DocumentsData>
)

data class MetaData(
    val total_count : Int,
    val pageable_count : Int,
    val is_end : Boolean
)

data class DocumentsData(
//    val datatime : String,
    val contents : String,
    val title : String
//    val url : String

)