package com.example.sopt_27_android.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.sopt_27_android.*
import com.example.sopt_27_android.fragment.MeFragment
import com.example.sopt_27_android.fragment.PortFolioFragment
import com.example.sopt_27_android.fragment.SettingFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private lateinit var viewpagerAdapter : ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 뷰페이저 세팅
        viewpagerAdapter =
            ViewPagerAdapter(supportFragmentManager)
        viewpagerAdapter.fragments = listOf(
            MeFragment(),
            PortFolioFragment(),
            SettingFragment()
        )
        main_viewpager.adapter = viewpagerAdapter


        // ViewPager 슬라이드 시 BottomNavi 변경
        main_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            // ViewPager의 페이지 중 하나가 선택된 경우
            override fun onPageSelected(position: Int) {
                main_bottom_navi.menu.getItem(position).isChecked = true
            }
        })

        //바텀 네비게이션 세팅
        main_bottom_navi.setOnNavigationItemSelectedListener {
            var index by Delegates.notNull<Int>()
            when (it.itemId) {
                R.id.menu_me -> index = 0
                R.id.menu_portfolio -> index = 1
                R.id.menu_setting -> index = 2
            }
            main_viewpager.currentItem = index
            true
        }


        // ISLOGIN 이라는 파일 안에 사용자 로그인 상태 저장됨
        val loginpref = getSharedPreferences("ISLOGIN", Context.MODE_PRIVATE)
        val editor = loginpref.edit()
        editor.putBoolean("AUTO_LOGIN", true)
        editor.apply()
    }
}

