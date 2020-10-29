package com.example.sopt_27_android

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // 회원가입 버튼
        btn_join.setOnClickListener{
            if(et_joinid.text.isNullOrBlank() || et_joinpw.text.isNullOrBlank()){
                Toast.makeText(this, "빈칸이 있습니다.", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "회원가입 되었습니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SignInActivity::class.java)

                // 회원가입에 사용한 정보가 로그인 창에서 자동으로 입력되어 있게 함
                intent.putExtra("id", et_joinid.text.toString())
                intent.putExtra("pw", et_joinpw.text.toString())

                // SignUpActivity 종료 시점에 작성함.
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }
}