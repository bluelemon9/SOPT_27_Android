package com.example.sopt_27_android.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sopt_27_android.MySharedPreferences
import com.example.sopt_27_android.R
import com.example.sopt_27_android.TabAdapter
import com.example.sopt_27_android.activity.SignInActivity
import kotlinx.android.synthetic.main.fragment_me.*


class MeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_me, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 로그아웃 버튼
        btn_logout.setOnClickListener {
            MySharedPreferences.clearUser(view.context)
            val intent = Intent(view.context, SignInActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }


        val tabAdapter = TabAdapter(childFragmentManager)
        tab_viewpager.adapter = tabAdapter

        // Tablayout과 연동
        tablayout.setupWithViewPager(tab_viewpager)
        tablayout.apply {
            getTabAt(0)?.text = "INFO"
            getTabAt(1)?.text = "OTHER"
        }
//        tabAdapter.notifyDataSetChanged()
    }
}