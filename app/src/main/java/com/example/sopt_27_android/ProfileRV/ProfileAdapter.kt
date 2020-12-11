package com.example.sopt_27_android.ProfileRV

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sopt_27_android.R

// 서버 연동
class ProfileAdapter(private val context : Context, var datas : MutableList<ProfileData>)
    : RecyclerView.Adapter<ProfileViewHolder>(), ItemActionListener {

// 더미용
//class ProfileAdapter (private val context : Context) : RecyclerView.Adapter<ProfileViewHolder>() {
//    var data = mutableListOf<ProfileData>()

    var changeViewType = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view =
            when(viewType){
                1-> { //리니어
                    LayoutInflater.from(context).inflate(
                        R.layout.item_profile_linear,
                        parent, false)
                }
                2-> { //그리드
                    LayoutInflater.from(context).inflate(
                        R.layout.item_profile_grid,
                        parent, false)
                }
                else -> {
                    LayoutInflater.from(context).inflate(
                        R.layout.item_profile_linear,
                        parent, false)
                }
            }
        return ProfileViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return changeViewType
    }

//    // 더미용
//    override fun getItemCount(): Int = data.size
//
//    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
//        holder.onBind(data[position])
//    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.onBind(datas[position])
    }


    // 드래그해서 위치 이동
    override fun onItemMoved(from: Int, to: Int) {
        if(from == to){
            return
        }
        val fromItem = datas.removeAt(from)
        datas.add(to, fromItem)
        notifyItemMoved(from, to)
    }

    // 스와이프해서 삭제
    override fun onItemSwiped(position: Int) {
        datas.removeAt(position)
        notifyItemRemoved(position)
    }
}