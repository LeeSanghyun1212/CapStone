package com.example.test2

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.io.Serializable

class ChoiceActivity : AppCompatActivity() {

    // Firebase 참조 설정
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice)

        val checkBox1 = findViewById<CheckBox>(R.id.checkBox)
        val checkBox2 = findViewById<CheckBox>(R.id.checkBox2)
        val checkBox3 = findViewById<CheckBox>(R.id.checkBox3)

        val submitButton = findViewById<Button>(R.id.submit_button)

        submitButton.setOnClickListener {
            val selectedOptions = mutableListOf<HashMap<String, String>>()

            if (checkBox1.isChecked) {
                val option = hashMapOf(
                    "text" to checkBox1.text.toString(),
                    "value" to (checkBox1.tag?.toString() ?: "")
                )
                selectedOptions.add(option)
            }

            if (checkBox2.isChecked) {
                val option = hashMapOf(
                    "text" to checkBox2.text.toString(),
                    "value" to (checkBox2.tag?.toString() ?: "")
                )
                selectedOptions.add(option)
            }

            if (checkBox3.isChecked) {
                val option = hashMapOf(
                    "text" to checkBox3.text.toString(),
                    "value" to (checkBox3.tag?.toString() ?: "")
                )
                selectedOptions.add(option)
            }

            sendToFirebase(selectedOptions)
        }
    }

    private fun sendToFirebase(selectedOptions: List<HashMap<String, String>>) {
        val data = hashMapOf(
            "과목" to selectedOptions,

            )

        // 'graduate' 컬렉션에 데이터 추가
        db.collection("graduate")
            .add(data)
            .addOnSuccessListener { documentReference ->
                println("DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                println("Error adding document: $e")
            }
    }
}
