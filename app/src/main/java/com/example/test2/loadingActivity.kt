package com.example.test2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class loadingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        // 로딩 화면 시작.
        loadingStart()
    }

    private fun loadingStart() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent = Intent(applicationContext, loginActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}