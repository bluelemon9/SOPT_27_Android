package com.example.sopt_27_android.KakaowebRV

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sopt_27_android.R
import com.example.sopt_27_android.data.DocumentsData

class WebViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tv_searchtitle = itemView.findViewById<TextView>(R.id.tv_searchtitle)
    val tv_searchcontents = itemView.findViewById<TextView>(R.id.tv_searchcontents)

    fun bind(webData : DocumentsData) {
        tv_searchtitle.text = webData.title
        tv_searchcontents.text = webData.contents
    }
}