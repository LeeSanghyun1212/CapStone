package com.example.test2

import android.content.Intent
import android.os.Bundle
import android.os.PatternMatcher
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
import android.view.KeyEvent
class RegisterActivity : AppCompatActivity() {
    private lateinit var create: Button
    private lateinit var email: EditText // 이메일
    private lateinit var pw: EditText // 비번
    private lateinit var rpw: EditText //비번 중복
    private lateinit var realname: EditText // 실제 이름
    private lateinit var phone: EditText // 폰번호
    private lateinit var grade: EditText //학년
    private lateinit var btnCheckEmail: Button
    var CheckEmail: Boolean = false
    private lateinit var btnCheckpw: Button
    var CheckPw: Boolean = false

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var db: FirebaseFirestore  // Firestore 인스턴스 선언

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        create = binding.complete
        email = binding.email
        pw = binding.password
        rpw = binding.password2
        realname = binding.realname
        phone = binding.phone
        grade = binding.grade

        // FirebaseAuth 및 Firestore 인스턴스 초기화
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        btnCheckEmail = findViewById(R.id.btnCheckEmail)
        btnCheckpw = findViewById(R.id.btnCheckpw)

        btnCheckEmail.setOnClickListener {
            checkEmail()
        }
        btnCheckpw.setOnClickListener {
            checkpassword()
        }
        create.setOnClickListener() {
            if(CheckEmail&&CheckPw){
                val enteredEmail = email.text.toString()
                val enteredPassword = pw.text.toString()
                val enteredrealname = realname.text.toString()
                val enteredphone = phone.text.toString()
                val enteredgrade = grade.text.toString()
                val enteredRePassword = rpw.text.toString()
                if (enteredEmail.isNullOrEmpty() || enteredPassword.isNullOrEmpty() ||
                    enteredrealname.isNullOrEmpty() || enteredphone.isNullOrEmpty() ||
                    enteredgrade.isNullOrEmpty() || enteredRePassword.isNullOrEmpty()
                ) {
                    Toast.makeText(this, "모든 필수 입력 값을 입력해주세요", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                auth.createUserWithEmailAndPassword(enteredEmail, enteredPassword)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            val userMap = hashMapOf(
                                //필드 추가
                                "email" to enteredEmail,
                                "pw" to enteredPassword,
                                "realname" to enteredrealname,
                                "phone" to enteredphone,
                                "grade" to enteredgrade
                            )
                            db.collection("users").document(user?.uid ?: "").set(userMap)
                                .addOnSuccessListener {
                                    Toast.makeText(this, "회원가입 및 데이터 저장 완료", Toast.LENGTH_LONG)
                                        .show()
                                    finish()
                                }
                                .addOnFailureListener { e ->
                                    Toast.makeText(this, "데이터 저장 실패: $e", Toast.LENGTH_SHORT)
                                        .show()
                                    // 다른 오류 처리 작업을 추가할 수 있습니다.
                                }
                        } else {
                            Toast.makeText(
                                this,
                                "회원가입 실패: ${task.exception?.localizedMessage}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
            else{
                Toast.makeText(this,"중복확인 버튼을 눌러 확인해주세요",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkpassword(){
        val userpw = pw.text.toString()
        val userRpw = rpw.text.toString()
        if (userpw != userRpw){
            Toast.makeText(this, "비밀번호와 비밀번호 확인이 일치하지 않습니다",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, "비밀번호와 비밀번호 확인이 일치합니다.",Toast.LENGTH_SHORT).show()
            CheckPw=true
        }
    }
    private fun checkEmail(){
        val userEmail = email.text.toString()
        if (userEmail.isNullOrEmpty()) {
            Toast.makeText(this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show()
        }
        else{
            // Firebase Authentication에서 이메일 중복 확인
            auth.fetchSignInMethodsForEmail(userEmail).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val signInMethods = task.result?.signInMethods
                    if (signInMethods.isNullOrEmpty()) {
                        // 이메일이 중복되지 않은 경우
                        // Firestore에서도 중복 확인
                        val userRef = db.collection("users").whereEqualTo("email", userEmail)
                        userRef.get().addOnCompleteListener { firestoreTask ->
                            if (firestoreTask.isSuccessful) {
                                val documents = firestoreTask.result?.documents
                                if (documents.isNullOrEmpty()) {
                                    // Firestore에도 중복된 이메일이 없는 경우
                                    Toast.makeText(this, "사용 가능한 이메일입니다.", Toast.LENGTH_SHORT).show()
                                } else {
                                    // Firestore에 이미 등록된 이메일이 있는 경우
                                    Toast.makeText(this, "이미 가입된 이메일입니다.", Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                // Firestore에서의 중복 확인 실패
                                Toast.makeText(this, "Firestore에서 이메일 중복 확인 실패", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    } else {
                        // 이미 해당 이메일로 가입된 계정이 있는 경우
                        Toast.makeText(this, "이미 가입된 이메일입니다.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Firebase Authentication에서의 중복 확인 실패
                    Toast.makeText(this, "Firebase에서 이메일 중복 확인 실패", Toast.LENGTH_SHORT).show()
                }
            }
            CheckEmail=true;
        }
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}