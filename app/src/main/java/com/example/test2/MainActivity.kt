package com.example.test2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test2.databinding.ActivityMainBinding
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity(){
    private lateinit var ConfirmBtn: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding
// 설계학점이나 MSC 학점 추가해야함.
    private val subjects1_1 = listOf(
        Subject(false,"일반생물학실험 및 실험",1,1.0,0.0),
        Subject(false,"공학설계입문",2, 0.0,2.0),
        Subject(false,"논리수학",3,3.0,0.0),
        Subject(false,"Web프로그래밍 1",3,0.0,0.0)
    )
    private val subjects1_2 = listOf(
        Subject(false,"WEB프로그래밍 2",3,0.0,1.0),
        Subject(false,"컴퓨터프로그래밍",3,0.0,0.0),
        Subject(false,"전산수학및정수론",3,3.0,0.0),
    )
    private val subjects2_1 = listOf(
        Subject(false,"C++프로그래밍",3,0.0,1.0),
        Subject(false,"Java프로그래밍1",3,0.0,0.0),
        Subject(false,"이산구조",3, 3.0,0.0),
    )
    private val subjects2_2 = listOf(
        Subject(false,"윈도우즈프로그래밍",3,0.0,1.0),
        Subject(false,"확률및통계실습",3,3.0,0.0),
        Subject(false,"Java프로그래밍2",3, 1.5,0.0),
        Subject(false,"데이터구조",3,0.0,0.0),
        Subject(false,"운영체제",3,0.0,0.0),
    )
    private val subjects3_1 = listOf(
        Subject(false,"객체지향윈도우즈프로그래밍",3,0.0,1.5),
        Subject(false,"알고리즘",3,0.0,0.0),
    )
    private val subjects3_2 = listOf(
        Subject(false,"소프트웨어요구분석및설계",3,0.0,1.5),
        Subject(false,"수치해석",3,3.0,0.0),
        Subject(false,"컴퓨터구조",3,0.0,0.0) ,
        Subject(false,"캡스톤디자인 1",3,0.0,3.0),
    )
    private val subjects4_1 = listOf(
        Subject(false,"캡스톤디자인2",3,0.0,3.0)
    )
    private val subjects4_2 = listOf(
        Subject(false,"객체지향윈도우즈프로그래밍",3,0.0,1.5),
        Subject(false,"알고리즘",3,0.0,0.0),
        Subject(false,"수치해석",3, 3.0,0.0),
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        initViews()
        ConfirmBtn = findViewById(R.id.Confirmbtn)
        ConfirmBtn.setOnClickListener {
            val selectedSubjects= mutableListOf<Subject>()
            val allSubjectsLists = listOf(subjects1_1, subjects1_2, subjects2_1, subjects2_2, subjects3_1, subjects3_2, subjects4_1, subjects4_2)
            allSubjectsLists.forEach { subjectsList ->
                subjectsList.forEach { subject ->
                    if (subject.completed) {
                        selectedSubjects.add(subject)
                    }
                }
            }
            // 스코어, MSC 및 디자인 값을 계산
            var totalScore = 0
            var totalMSC = 0.0
            var totalDesign = 0.0

            selectedSubjects.forEach { subject ->
                totalScore += subject.score
                totalMSC += subject.MSC
                totalDesign += subject.Design
            }

            val intent = Intent(this, NaviActivity::class.java)
            intent.putExtra("totalScore", totalScore)
            intent.putExtra("totalMSC", totalMSC)
            intent.putExtra("totalDesign", totalDesign)
            startActivity(intent)
        }
    }
    private fun initViews() {
        val layoutManager1 = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.subjectList1.layoutManager = layoutManager1
        binding.subjectList1.adapter = SubjectAdapter(subjects1_1)

        val layoutManager2 = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.subjectList2.layoutManager = layoutManager2
        binding.subjectList2.adapter = SubjectAdapter(subjects1_2)

        val layoutManager3 = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.subjectList3.layoutManager = layoutManager3
        binding.subjectList3.adapter = SubjectAdapter(subjects2_1)

        val layoutManager4 = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.subjectList4.layoutManager = layoutManager4
        binding.subjectList4.adapter = SubjectAdapter(subjects2_2)

        val layoutManager5 = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.subjectList5.layoutManager = layoutManager5
        binding.subjectList5.adapter = SubjectAdapter(subjects3_1)

        val layoutManager6 = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.subjectList6.layoutManager = layoutManager6
        binding.subjectList6.adapter = SubjectAdapter(subjects3_2)

        val layoutManager7 = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.subjectList7.layoutManager = layoutManager7
        binding.subjectList7.adapter = SubjectAdapter(subjects4_1)
    }
}