package com.example.sopt_27_android.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sopt_27_android.ProfileRV.ProfileAdapter
import com.example.sopt_27_android.ProfileRV.ProfileData
import com.example.sopt_27_android.R
import kotlinx.android.synthetic.main.fragment_port_folio.*

class PortFolioFragment : Fragment() {
    private lateinit var profileAdapter: ProfileAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 아래 한줄이 있어야 액티비티보다 프레그먼트의 메뉴가 우선시 됨
        setHasOptionsMenu(true)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_port_folio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // 어댑터에 context 객체를 파라미터로 전달
        profileAdapter = ProfileAdapter(view.context)

        rv_profile.adapter = profileAdapter
        rv_profile.layoutManager = LinearLayoutManager(view.context)

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
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
//            R.id.logout -> {
//                val mainintent = Intent(MainActivity)
//            }

            R.id.linear -> {
                profileAdapter.changeViewType = 1
                rv_profile.apply {
                    layoutManager = LinearLayoutManager(view?.context)
                }
            }
            R.id.grid -> {
                profileAdapter.changeViewType = 2
                rv_profile.apply {
                    layoutManager = GridLayoutManager(view?.context, 2)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

