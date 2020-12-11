package com.example.sopt_27_android.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sopt_27_android.KakaowebRV.WebAdapter
import com.example.sopt_27_android.R
import com.example.sopt_27_android.data.ResponseWebData
import com.example.sopt_27_android.network.KakaoWebServiceImpl
import kotlinx.android.synthetic.main.fragment_setting.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchbar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(searchtext: Editable?) {

            }

            override fun beforeTextChanged(searchtext: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(searchtext: CharSequence?, p1: Int, p2: Int, p3: Int) {
                loadDatas(searchtext.toString())
            }
        })
    }

    fun loadDatas(searchtext: String) {
        // 서버 요청
        KakaoWebServiceImpl.webservice.requestWeb(searchtext)
            .enqueue(object : Callback<ResponseWebData> { // Callback 등록, Retrofit의 Callback을 import
                override fun onFailure(call: Call<ResponseWebData>, t: Throwable) {
                    // 통신 실패
                    Log.d("카카오 웹 통신 실패", "$t")
                }

                override fun onResponse(
                    call: Call<ResponseWebData>,
                    response: Response<ResponseWebData>
                ) {
                    // 통신 성공
                    if (response.isSuccessful) {  // statusCode가 200-300 사이일 때, 응답 body 이용 가능
                        Log.d("성공", "${response.body()}")

                        val webAdapter = WebAdapter(view!!.context, response.body()!!.documents)
                        webAdapter.notifyDataSetChanged()
                        rv_web.adapter = webAdapter

                    } else {
                        Log.d("에러", "${response.body()}")
                    }
                }
            })
    }

}