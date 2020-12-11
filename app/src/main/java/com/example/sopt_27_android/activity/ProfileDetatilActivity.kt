package com.example.sopt_27_android.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sopt_27_android.R
import kotlinx.android.synthetic.main.activity_profile_detatil.*

class ProfileDetatilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_detatil)

        // 리사이클러뷰에 있던 각 아이템의 정보 받아옴
        var title= intent.getStringExtra("title")
        var subtitle= intent.getStringExtra("subtitle")

        tv_title.setText(title)
        tv_subtitle.setText(subtitle)
    }
}