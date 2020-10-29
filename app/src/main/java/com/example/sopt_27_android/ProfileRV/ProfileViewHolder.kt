package com.example.sopt_27_android.ProfileRV

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.sopt_27_android.ProfileDetatilActivity
import com.example.sopt_27_android.R

class ProfileViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
    private val title = itemView.findViewById<TextView>(R.id.item_title)
    private val subtitle = itemView.findViewById<TextView>(R.id.item_subtitle)

    val profile_item = itemView.findViewById<ConstraintLayout>(R.id.profile_item)

    fun onBind(profiledata : ProfileData){
        title.text = profiledata.title
        subtitle.text = profiledata.subtitle

        //프로필 아이템 버튼 클릭 이벤트 (상세정보)
        profile_item.setOnClickListener(object :View.OnClickListener {
            override fun onClick(v: View?) {
                val context: Context = v!!.context
                val detailIntent = Intent(v!!.context, ProfileDetatilActivity::class.java)
                detailIntent.putExtra("title",profiledata.title)
                detailIntent.putExtra("subtitle",profiledata.subtitle)
                context.startActivity(detailIntent)
            }
        })
    }
}