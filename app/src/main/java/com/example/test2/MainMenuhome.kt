package com.example.test2

import android.view.animation.DecelerateInterpolator
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test2.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import android.util.Log
import android.widget.ProgressBar
import androidx.fragment.app.activityViewModels
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class MainMenuhome : Fragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var majorScoreTextView: TextView
    private lateinit var cultureScoreTextView: TextView
    private lateinit var mscScoreTextView: TextView
    private lateinit var designScoreTextView: TextView
    private lateinit var totalsScoreTextView: TextView
    private lateinit var binding: ActivityMainBinding
    private lateinit var progressBar: ProgressBar
    private lateinit var percent: TextView
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val receivedData = arguments?.getString("key")
        arguments?.let {
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityMainBinding.inflate(inflater, container, false)
        db = FirebaseFirestore.getInstance()
        val userID = FirebaseAuth.getInstance().currentUser?.uid ?: ""
        val userCoursesRef = db.collection("users").document(userID).collection("courses")

        val view = inflater.inflate(R.layout.fragment_main_menu_home, container, false)
        val welcomeMessage: TextView = view.findViewById(R.id.rectangle_6)
        val progressBar2 : ProgressBar =  view.findViewById(R.id.Maxprogressbar)

        progressBar = view.findViewById(R.id.Maxprogressbar)
        majorScoreTextView = view.findViewById(R.id.MajorScore)
        cultureScoreTextView = view.findViewById(R.id.CultureScore)
        mscScoreTextView = view.findViewById(R.id.MscScore)
        designScoreTextView = view.findViewById(R.id.DesignScore)
        totalsScoreTextView = view.findViewById(R.id.Score)
        percent = view.findViewById(R.id.percent)

        // 전달받은 이름 정보 가져오기
        val realname = arguments?.getString("realname")
        Log.d("RealNameDebug", "Realname: $realname")
        if (realname != null) {
            welcomeMessage.text = "${realname}님 어서오세요."
        }

        userCoursesRef.get()
            .addOnSuccessListener { documents ->
                var totalscore = 0.0
                var majorscore = 0.0
                var culturescore = 0.0
                var mscscore = 0.0
                var designscore = 0.0

                for (document in documents) {
                    // documents에서 필요한 데이터 필드를 가져와서 처리
                    val score = document.getDouble("score") ?: 0.0
                    val score2 = document.getDouble("score2") ?: 0.0
                    val Design = document.getDouble("Design") ?: 0.0
                    val MSC = document.getDouble("MSC") ?: 0.0
                    majorscore+=score
                    culturescore+=score2
                    designscore+=Design
                    mscscore+=MSC
                }
                totalscore = majorscore + culturescore
                val progress = ((totalscore / 130.0) * 100).toInt()
                progressBar.progress = progress
                percent.text = "$progress%"
                ObjectAnimator.ofInt(progressBar2, "progress", 0, progress)
                    .apply {
                        duration = 1000 // 애니메이션 지속 시간 (2초로 설정)
                        start()
                    }
                totalsScoreTextView.text="$totalscore / 130.0"
                majorScoreTextView.text = "$majorscore / 84.0"
                cultureScoreTextView.text = "$culturescore / 24.0"
                mscScoreTextView.text = "$mscscore / 18.0"
                designScoreTextView.text = "$designscore / 13.0"
                // 데이터를 가져오는 데 성공한 경우 처리할 내용을 여기에 추가하세요.
            }
            .addOnFailureListener { exception ->
                // 데이터 가져오기에 실패한 경우 처리할 내용을 여기에 추가하세요.
            }
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 공유 ViewModel의 LiveData를 관찰하여 화면 업데이트
        sharedViewModel.totalScore.observe(viewLifecycleOwner, { totalscore ->
            totalsScoreTextView.text = "$totalscore / 130.0"
            val progress = ((totalscore / 130.0) * 100).toInt()
            progressBar.progress = progress
            percent.text = "$progress%"
        })
        sharedViewModel.totalMajorScoreLiveData.observe(viewLifecycleOwner, { totalmajorScore ->
            majorScoreTextView.text = "$totalmajorScore / 84.0"
        })
        sharedViewModel.totalCultureScoreLiveData.observe(viewLifecycleOwner, { totalcultureScore ->
            cultureScoreTextView.text = "$totalcultureScore / 24.0"
        })
        sharedViewModel.totalMSCScoreLiveData.observe(viewLifecycleOwner, { totalmscscore ->
            mscScoreTextView.text = "$totalmscscore / 18.0"
        })
        sharedViewModel.totalDesignScoreLiveData.observe(viewLifecycleOwner, { totaldesignscore ->
            designScoreTextView.text = "$totaldesignscore / 13.0"
        })

    }
    companion object {
        @JvmStatic
        fun newInstance(realname: String): MainMenuhome {
            val fragment = MainMenuhome()
            val args = Bundle()
            args.putString("realname", realname)
            fragment.arguments = args
            return fragment
        }
    }
}