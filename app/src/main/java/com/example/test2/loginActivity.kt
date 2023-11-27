package com.example.test2

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class loginActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //파이어 베이스 초기화
        auth = FirebaseAuth.getInstance()
        //로그인 버튼 클릭시 이벤트 처리
        emailEditText = findViewById(R.id.Id)
        passwordEditText = findViewById(R.id.Pw)
        loginButton = findViewById(R.id.btn_login)

        loginButton.setOnClickListener {

            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            // Firebase를 사용하여 로그인 시도
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "이메일 또는 비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // 로그인 성공
                            Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, ChoiceActivity::class.java)
                            startActivity(intent)
                            // 현재 액티비티를 종료합니다.
                            finish()
                        } else {
                            // 로그인 실패
                            Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
        //회원가입 버튼 클릭시 회원가입으로 이동
        val button: Button = findViewById(R.id.btn_register)
        button.setOnClickListener {
            Toast.makeText(this,"hi",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)

        }
    }
}
