package com.example.test2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.test2.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.FirebaseFirestore
class RegisterActivity : AppCompatActivity() {
    private lateinit var create: Button
    private lateinit var email: EditText
    private lateinit var pw: EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var db: FirebaseFirestore  // Firestore 인스턴스 선언

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        create = binding.createBtn
        email = binding.email
        pw = binding.password

        // FirebaseAuth 및 Firestore 인스턴스 초기화
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        create.setOnClickListener() {
            val enteredEmail = email.text.toString()
            val enteredPassword = pw.text.toString()

            if (enteredEmail.isNullOrEmpty() || enteredPassword.isNullOrEmpty()) {
                Toast.makeText(this, "이메일 혹은 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            } else {
                auth.createUserWithEmailAndPassword(enteredEmail, enteredPassword).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        val userMap = hashMapOf(
                            "email" to enteredEmail,
                            "pw" to enteredPassword
                            // 필요한 추가 필드를 여기에 추가할 수 있습니다.
                        )

                        db.collection("users").document(user?.uid ?: "").set(userMap)
                            .addOnSuccessListener {
                                Toast.makeText(this, "회원가입 및 데이터 저장 완료", Toast.LENGTH_LONG).show()
                                finish()
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "데이터 저장 실패: $e", Toast.LENGTH_SHORT).show()
                                // 다른 오류 처리 작업을 추가할 수 있습니다.
                            }
                    }



                    else
                    {
                        Toast.makeText(this, "이메일 형식인지 확인 또는 비밀번호 6자리 이상 입력해주세요!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}