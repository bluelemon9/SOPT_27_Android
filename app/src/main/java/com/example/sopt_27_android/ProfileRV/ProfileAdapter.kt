package com.example.sopt_27_android.ProfileRV

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sopt_27_android.R

class ProfileAdapter (private val context : Context) : RecyclerView.Adapter<ProfileViewHolder>() {
    var data = mutableListOf<ProfileData>()
    var changeViewType = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
//        val view = LayoutInflater.from(context).inflate(
//            R.layout.item_profile_linear,
//            parent, false

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

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.onBind(data[position])
    }
}