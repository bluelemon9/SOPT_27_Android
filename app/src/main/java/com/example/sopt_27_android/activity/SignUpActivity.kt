package com.example.sopt_27_android.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sopt_27_android.R
import com.example.sopt_27_android.data.RequestSignUp
import com.example.sopt_27_android.data.ResponseSignUp
import com.example.sopt_27_android.network.SignServiceImpl
import kotlinx.android.synthetic.main.activity_sign_up.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
                // 서버 통신
                SignServiceImpl.signupservice.requestSignUp(
                    RequestSignUp(
                        email = et_joinid.text.toString(),
                        password = et_joinpw.text.toString(),
                        userName = et_joinname.text.toString()
                    )   // 회원가입 정보를 전달
                ).enqueue(object : Callback<ResponseSignUp> {
                    override fun onResponse(
                        call: Call<ResponseSignUp>,
                        response: Response<ResponseSignUp>
                    ) {
                        response.takeIf { it.isSuccessful}
                            ?.body()
                            ?.let { it ->
                                    Toast.makeText(this@SignUpActivity, "회원가입 되었습니다.", Toast.LENGTH_SHORT).show()
                                    val intent = Intent(this@SignUpActivity, SignInActivity::class.java)

                                    // 회원가입에 사용한 정보가 로그인 창에서 자동으로 입력되어 있게 함
                                    intent.putExtra("id", et_joinid.text.toString())
                                    intent.putExtra("pw", et_joinpw.text.toString())

                                    // SignUpActivity 종료 시점에 작성함.
                                    setResult(Activity.RESULT_OK, intent)
                                    finish()

                            } ?: showError(response.errorBody())
                    }
                    override fun onFailure(call: Call<ResponseSignUp>, t: Throwable) {
                        Toast.makeText(this@SignUpActivity, "회원가입 실패", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }

    private fun showError(error : ResponseBody?){
        val e = error ?: return
        val ob = JSONObject(e.string())
        Toast.makeText(this, ob.getString("message"),Toast.LENGTH_SHORT).show()
    }
}