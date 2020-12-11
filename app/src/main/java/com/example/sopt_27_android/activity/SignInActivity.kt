package com.example.sopt_27_android.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sopt_27_android.MySharedPreferences
import com.example.sopt_27_android.R
import com.example.sopt_27_android.data.RequestSignIn
import com.example.sopt_27_android.data.ResponseSignIn
import com.example.sopt_27_android.network.SignServiceImpl
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {
    val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        // SharedPreferences에 정보가 저장되어 있지 않을 때 -> 일반 로그인
        if(MySharedPreferences.getId(this).isBlank() || MySharedPreferences.getPw(this).isBlank()) {
            // 로그인 버튼
            btn_login.setOnClickListener {
                if (et_id.text.isNullOrBlank() || et_pw.text.isNullOrBlank()) {
                    Toast.makeText(this, "빈칸이 있습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    // 서버 통신
                    SignServiceImpl.signinservice.requestSignIn(
                        RequestSignIn(
                            email = et_id.text.toString(),
                            password = et_pw.text.toString()
                        )
                    ).enqueue(object : Callback<ResponseSignIn> {
                        override fun onResponse(
                            call: Call<ResponseSignIn>,
                            response: Response<ResponseSignIn>
                        ) {
                            if (response.isSuccessful) {
                                // 자동로그인 값 저장
                                MySharedPreferences.setId(applicationContext, et_id.text.toString())
                                MySharedPreferences.setPw(applicationContext, et_pw.text.toString())

                                Toast.makeText(this@SignInActivity, "로그인 되었습니다.", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@SignInActivity, MainActivity::class.java)
                                startActivity(intent)
                            } else {
                                showError(response.errorBody())
                            }
                        }

                        override fun onFailure(call: Call<ResponseSignIn>, t: Throwable) {
                            Toast.makeText(this@SignInActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }
        } else {
            // SharedPreferences에 값이 저장되어 있을 때 -> 자동 로그인
            Toast.makeText(this, "자동로그인 되었습니다.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // 회원가입 버튼
        tv_join.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }


//        // 자동로그인 -> 종료 시 다시 접속해도 로그인 유지될 수 있도록 로그인 상태 저장
//        val loginpref = getSharedPreferences("ISLOGIN", Context.MODE_PRIVATE)
//        if (loginpref.getBoolean("AUTO_LOGIN", false)) {
//            startActivity(Intent(this, MainActivity::class.java))
//        }
    }


    private fun showError(error : ResponseBody?){
        val e = error ?: return
        val ob = JSONObject(e.string())
        Toast.makeText(this, ob.getString("message"),Toast.LENGTH_SHORT).show()
    }

    // onActivityResult: main액티비티에서 sub액티비티를 호출하여 넘어갔다가, 다시 main 액티비티로 돌아올때 사용되는 기본 메소드
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                // 로그인 입력창에 회원가입 성공한 id와 pw가 입력되도록 함
                et_id.setText(data?.getStringExtra("id"))
                et_pw.setText(data?.getStringExtra("pw"))

//                // 자동로그인 -> 어플 삭제되기 전까지 로그인된 계정 정보 저장
//                // Context를 통해 접근, "USER"이라는 이름으로 파일이 저장되며 Editor를 이용해 값 저정
//                val pref = getSharedPreferences("USER", Context.MODE_PRIVATE)
//                val editor = pref.edit()
//
//                editor.putString("id", et_id.text.toString())
//                editor.putString("pw", et_pw.text.toString())
//                editor.apply()
            } else {
                Toast.makeText(this, "값 전달 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }
}