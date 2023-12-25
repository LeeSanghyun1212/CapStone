package com.example.test2

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Context
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test2.databinding.FragmentMainMenuMajorBinding
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainMenumajor.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainMenumajor : Fragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var editTextFragment: EditText
    private lateinit var db: FirebaseFirestore
    private lateinit var binding: FragmentMainMenuMajorBinding

    lateinit var Addbutton: Button
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
    private val subjectsculture = listOf(
        Subject2(false,"공학글쓰기",2),
        Subject2(false,"인재세내기세미나",1),
        Subject2(false,"대학영어회화 1",2),
        Subject2(false,"지식재산권의 이해",3),
        Subject2(false,"과학기술과직업윤리",3),
        Subject2(false,"생애설계와비전탐색",1),
        Subject2(false,"데이터사이언스기초",2),
        Subject2(false,"대학영어1",2),
        Subject2(false,"파이썬 활용",2),
        Subject2(false,"진로탐구와역량개발",2),
        Subject2(false,"경영경제학",3)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMainMenuMajorBinding.inflate(layoutInflater)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.Confirmbtn.setOnClickListener {
            val inputText = binding.ff.text.toString()

            val sharedPreferences = requireActivity().getSharedPreferences("EditText_Value", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("editTextValue", inputText)
            editor.apply()

            // 기존의 Confirmbtn 클릭 시 동작하는 코드...
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_main_menu_major, container, false)
        val binding = FragmentMainMenuMajorBinding.inflate(inflater, container, false)
        db = FirebaseFirestore.getInstance()


        val userID = FirebaseAuth.getInstance().currentUser?.uid ?: ""
        val userCoursesRef = db.collection("users").document(userID).collection("courses")
        val ff: EditText = binding.root.findViewById(R.id.ff)
        val sharedPreferences = requireActivity().getSharedPreferences("EditText_Value", Context.MODE_PRIVATE)
        val storedText = sharedPreferences.getString("editTextValue", "")
        binding.ff.setText(storedText) // 저장된 텍스트 설정

        // RecyclerView 설정
        val layoutManager1 =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.subjectList1.layoutManager = layoutManager1
        binding.subjectList1.adapter = SubjectAdapter(subjects1_1)
        val layoutManager2 =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.subjectList2.layoutManager = layoutManager2
        binding.subjectList2.adapter = SubjectAdapter(subjects1_2)
        val layoutManager3 =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.subjectList3.layoutManager = layoutManager3
        binding.subjectList3.adapter = SubjectAdapter(subjects2_1)
        val layoutManager4 =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.subjectList4.layoutManager = layoutManager4
        binding.subjectList4.adapter = SubjectAdapter(subjects2_2)
        val layoutManager5 =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.subjectList5.layoutManager = layoutManager5
        binding.subjectList5.adapter = SubjectAdapter(subjects3_1)
        val layoutManager6 =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.subjectList6.layoutManager = layoutManager6
        binding.subjectList6.adapter = SubjectAdapter(subjects3_2)
        val layoutManager7 =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.subjectList7.layoutManager = layoutManager7
        binding.subjectList7.adapter = SubjectAdapter(subjects4_1)
        val layoutManager8 =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.subjectList8.layoutManager = layoutManager8
        binding.subjectList8.adapter = SubjectAdapter2(subjectsculture)

        userCoursesRef.get()
            .addOnSuccessListener { documents ->
                val checkedSubjectsList = mutableListOf<Subject>()
                val checkedSubjectsList2 = mutableListOf<Subject2>()
                for (document in documents) {

                    val title = document.getString("name")
                    subjects1_1.find { it.title == title }?.completed = true
                    subjects1_2.find { it.title == title }?.completed = true
                    subjects2_1.find { it.title == title }?.completed = true
                    subjects2_2.find { it.title == title }?.completed = true
                    subjects3_1.find { it.title == title }?.completed = true
                    subjects3_2.find { it.title == title }?.completed = true
                    subjects4_1.find { it.title == title }?.completed = true
                    subjectsculture.find { it.title2 == title }?.completed2 = true

                }
                binding.subjectList1.adapter?.apply {
                    notifyDataSetChanged()
                }
                binding.subjectList2.adapter?.apply {
                    notifyDataSetChanged()
                }
                binding.subjectList3.adapter?.apply {
                    notifyDataSetChanged()
                }
                binding.subjectList4.adapter?.apply {
                    notifyDataSetChanged()
                }
                binding.subjectList5.adapter?.apply {
                    notifyDataSetChanged()
                }
                binding.subjectList6.adapter?.apply {
                    notifyDataSetChanged()
                }
                binding.subjectList7.adapter?.apply {
                    notifyDataSetChanged()
                }
                binding.subjectList8.adapter?.apply {
                    notifyDataSetChanged()
                }
            }
            .addOnFailureListener { exception ->
                //오류문 추가
            }

        val allSubjects = listOf(
            subjects1_1,
            subjects1_2,
            subjects2_1,
            subjects2_2,
            subjects3_1,
            subjects3_2,
            subjects4_1
        ).flatten()
        val confirmButton: Button = binding.root.findViewById(R.id.Confirmbtn)
        confirmButton.setOnClickListener {
            val inputText = binding.ff.text.toString()

            val doubleValue = inputText.toDoubleOrNull()
            if (doubleValue != null) {
                // 입력된 값이 유효한 Double 형일 때
                val editor = sharedPreferences.edit()
                editor.putString("editTextValue", doubleValue.toString()) // Double 값을 String으로 저장
                editor.apply()
            } else {
                // 올바르지 않은 입력일 때 사용자에게 메시지 표시
                Toast.makeText(requireContext(), "올바른 Double 값을 입력하세요.", Toast.LENGTH_SHORT).show()
            }

            val userID = FirebaseAuth.getInstance().currentUser?.uid ?: ""
            val userCoursesRef = db.collection("users").document(userID).collection("courses")
            val batch = db.batch()
            var totalmajorScore = 0.0
            var totalMSCScore = 0.0
            var totalDesignScore = 0.0
            var totalcultureScore = 0.0
            var totalculture = 0.0
            var totalScore = 0.0

            val intValue: Double = if (inputText.isNotEmpty()) {
                inputText.toDoubleOrNull() ?: 0.0
            } else {
                0.0
            }

            Log.d("RealNameDebug", "Realname: $inputText")
            for (subject in allSubjects) {
                if (subject.completed) {
                    totalmajorScore += subject.score
                    totalMSCScore += subject.MSC
                    totalDesignScore += subject.Design

                    val subjectData = hashMapOf(
                        "name" to subject.title,
                        "completed" to subject.completed,
                        "score" to subject.score,
                        "MSC" to subject.MSC,
                        "Design" to subject.Design
                    )
                    val subjectRef = userCoursesRef.document(subject.title)
                    batch.set(subjectRef, subjectData)
                } else {
                    val subjectRef = userCoursesRef.document(subject.title)
                    batch.delete(subjectRef)
                }
            }
            for (subject2 in subjectsculture) {
                if (subject2.completed2) {
                    totalcultureScore += subject2.score2
                    val cultureData = hashMapOf(
                        "name" to subject2.title2,
                        "completed" to subject2.completed2,
                        "score2" to subject2.score2,
                    )
                    val cultureRef = userCoursesRef.document(subject2.title2)
                    batch.set(cultureRef, cultureData)
                } else {
                    val cultureRef = userCoursesRef.document(subject2.title2)
                    batch.delete(cultureRef)
                }
            }
            val culture = hashMapOf(
                "score2" to intValue
            )

            val cultureRef22 = userCoursesRef.document("교양")
            batch.set(cultureRef22, culture)

            totalScore = totalmajorScore + totalcultureScore + intValue
            totalculture = totalcultureScore + intValue
            sharedViewModel.updateTotalMajorScore(totalmajorScore)
            sharedViewModel.updateTotalMSCScore(totalMSCScore)
            sharedViewModel.updateTotalDesignScore(totalDesignScore)
            sharedViewModel.updateTotalCultureScore(totalculture)
            sharedViewModel.updateTotalScore(totalScore)

            batch.commit()
            Toast.makeText(requireContext(), "저장되었습니다!", Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }




    companion object {
        fun newInstance(totalmajorScore: Double, totalMSCScore: Double, totalDesignScore: Double, totalcultureScore: Double, totalScore: Double): MainMenuhome {
            val fragment = MainMenuhome()
            val args = Bundle()
            args.putDouble("totalmajorScore", totalmajorScore)
            args.putDouble("totalMSCScore", totalMSCScore)
            args.putDouble("totalDesignScore", totalDesignScore)
            args.putDouble("totalcultureScore", totalcultureScore)
            args.putDouble("totalScore", totalScore)
            fragment.arguments = args
            return fragment
        }
    }
}

