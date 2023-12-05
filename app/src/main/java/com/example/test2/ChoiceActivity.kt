package com.example.test2

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.view.View
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth
import android.widget.Spinner
import android.widget.AdapterView
import java.io.Serializable

class ChoiceActivity : AppCompatActivity() {

    private lateinit var firstYearSubjectsList: ListView
    private lateinit var submit_button: Button
    private val db = FirebaseFirestore.getInstance()
    private val checkedItemsMap: MutableMap<Int, List<Pair<String, String>>> = mutableMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice)

        submit_button = findViewById(R.id.submit_button)

        firstYearSubjectsList = findViewById(R.id.firstYearSubjectsList)
        val spinner: Spinner = findViewById(R.id.spinner)

        val items = resources.getStringArray(R.array.my_array)
        val gradeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        spinner.adapter = gradeAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        val firstgrade = listOf(
                            "전산수학" to "1학점",
                            "공학설계" to "2학점",
                            "생명공학" to "3학점",
                            "fda" to "4",
                            "fdafa" to "5",
                        )
                        // ListView에 과목들을 보여줄 어댑터 설정
                        val adapter = CustomAdapter(this@ChoiceActivity, firstgrade)
                        firstYearSubjectsList.adapter = adapter
                    }
                    1 -> {
                        val secondgrade = listOf(
                            "제어프로그래밍" to "1학점"

                        )
                        val adapter = CustomAdapter(this@ChoiceActivity, secondgrade)
                        firstYearSubjectsList.adapter = adapter
                    }
                    // ... 다른 학년 선택 시 로직 추가
                    else -> {
                        // Handle other selections
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle case where nothing is selected
            }

        }
        submit_button.setOnClickListener {
            val selectedOptions = (firstYearSubjectsList.adapter as CustomAdapter).getCheckedItems()
            sendToFirebase(selectedOptions)
            Toast.makeText(this, "저장완료", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendToFirebase(selectedOptions: List<Pair<String, String>>) {
        val data = hashMapOf<String, Any>()

        val subjectMapList = selectedOptions.map { mapOf("과목" to it.first, "학점" to it.second) }

        data["grade"] = subjectMapList

        // Firebase에 데이터 추가
        val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid
        if (currentUserUid != null) {
            db.collection("users")
                .document(currentUserUid)
                .collection("Graduate")
                .add(data)
                .addOnSuccessListener { documentReference ->
                    println("DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    println("Error adding document: $e")
                }
        }
    }
}