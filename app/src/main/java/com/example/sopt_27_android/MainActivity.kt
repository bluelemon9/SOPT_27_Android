package com.example.sopt_27_android

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sopt_27_android.ProfileRV.ProfileAdapter
import com.example.sopt_27_android.ProfileRV.ProfileData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var profileAdapter: ProfileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ISLOGIN 이라는 파일 안에 사용자 로그인 상태 저장됨
        val loginpref = getSharedPreferences("ISLOGIN", Context.MODE_PRIVATE)
        val editor = loginpref.edit()
        editor.putBoolean("AUTO_LOGIN", true)
        editor.apply()

        // 어댑터에 context 객체를 파라미터로 전달
        profileAdapter =
            ProfileAdapter(this)

        rv_profile.adapter = profileAdapter
        rv_profile.layoutManager = LinearLayoutManager(this)

        // Scope Function을 이용하여 위의 코드를 아래처럼 간단하게 쓸 수 있음
//        rv_profile.apply{
//            adapter = profileAdapter
//            layoutManager = LinearLayoutManager(this)
//        }

        // 이 부분 26기 때랑 표현 방식 약간 다름
        profileAdapter.data = mutableListOf(
            ProfileData("이름", "김회진"),
            ProfileData("나이", "22"),
            ProfileData("파트", "안드로이드"),
            ProfileData(
                "GitHub",
                "www.github.com/bluelemon9"
            ),
            ProfileData(
                "Blog",
                "https://blog.naver.com/endjjsft"
            )
        )

        // Adapter에 데이터 갱신 알려줌
        profileAdapter.notifyDataSetChanged()
    }


    // 리사이클러뷰 레이아웃 선택 변경
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
//            R.id.logout -> {
//                val mainintent = Intent(MainActivity)
//            }

            R.id.linear -> {
                profileAdapter.changeViewType = 1
                rv_profile.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                }
            }
            R.id.grid -> {
                profileAdapter.changeViewType = 2
                rv_profile.apply {
                    layoutManager = GridLayoutManager(this@MainActivity,2)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}