package com.example.sopt_27_android.KakaowebRV

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sopt_27_android.R
import com.example.sopt_27_android.data.DocumentsData

class WebAdapter(private val context: Context, var datas: List<DocumentsData>) : RecyclerView.Adapter<WebViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_kakaoweb, parent, false)
        return WebViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: WebViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}