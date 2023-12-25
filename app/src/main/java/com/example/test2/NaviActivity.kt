package com.example.test2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.test2.databinding.ActivityNaviBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import androidx.activity.viewModels

private const val TAG_HOME = "home_schedule"
private const val TAG_MAJOR = "major_fragment"
private const val TAG_CULTURE = "culture_fragment"
private const val TAG_SCHEDULE = "schedule_fragment"
class SharedViewModel : ViewModel() {
    val checkedSubjects = MutableLiveData<List<Subject>>()
    val checkedSubjects2 = MutableLiveData<List<Subject2>>()
    // LiveData로 선언된 변수들
    private val _totalMajorScore = MutableLiveData<Double>()
    val totalMajorScoreLiveData: LiveData<Double>
        get() = _totalMajorScore

    private val _totalMSCScore = MutableLiveData<Double>()
    val totalMSCScoreLiveData: LiveData<Double>
        get() = _totalMSCScore

    private val _totalDesignScore = MutableLiveData<Double>()
    val totalDesignScoreLiveData: LiveData<Double>
        get() = _totalDesignScore

    private val _totalCultureScore = MutableLiveData<Double>()
    val totalCultureScoreLiveData: LiveData<Double>
        get() = _totalCultureScore

    private val _totalScore = MutableLiveData<Double>()
    val totalScore: LiveData<Double>
        get() = _totalScore

    private val _totalculture = MutableLiveData<Double>()
    val totalculture: LiveData<Double>
        get() = _totalculture

    private val _totalCultureScoreLiveData = MutableLiveData<Double>()
    val totalCultureScoreLiveData2: LiveData<Double>
        get() = _totalCultureScoreLiveData
    // 각 LiveData 값을 업데이트하는 함수
    fun updateTotalMajorScore(score: Double) {
        _totalMajorScore.value = score
    }

    fun updateTotalMSCScore(score: Double) {
        _totalMSCScore.value = score
    }

    fun updateTotalDesignScore(score: Double) {
        _totalDesignScore.value = score
    }

    fun updateTotalCultureScore(score: Double) {
        _totalCultureScore.value = score
    }
    fun updateTotalculture(score: Double) {
        _totalculture.value = score
    }
    fun updateTotalScore(score: Double) {
        _totalScore.value = score
    }

}
class NaviActivity : AppCompatActivity() {
    private lateinit var binding : ActivityNaviBinding
    private val sharedViewModel: SharedViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNaviBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val realname = intent.getStringExtra("realname")
        val fragment = MainMenuhome.newInstance(realname ?: "")
        setFragment(TAG_HOME, fragment)
        binding.navigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_major -> setFragment(TAG_MAJOR, MainMenumajor())
                R.id.menu_home -> setFragment(TAG_HOME, MainMenuhome())
                R.id.menu_schedule -> setFragment(TAG_SCHEDULE, MainMenuschedule())
                R.id.menu_culture -> setFragment(TAG_CULTURE, MainMenuculture())
            }
            true
        }

    }
    private fun setFragment(tag: String, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()

        if (manager.findFragmentByTag(tag) == null){
            fragTransaction.add(R.id.mainFrameLayout, fragment, tag)
        }

        val home = manager.findFragmentByTag(TAG_HOME)
        val major = manager.findFragmentByTag(TAG_MAJOR)
        val schedule = manager.findFragmentByTag(TAG_SCHEDULE)
        val culture = manager.findFragmentByTag(TAG_CULTURE)

        if (home != null){
            fragTransaction.hide(home)
        }

        if (major != null){
            fragTransaction.hide(major)
        }

        if (schedule != null) {
            fragTransaction.hide(schedule)
        }
        if (culture != null) {
            fragTransaction.hide(culture)
        }

        if (tag == TAG_HOME) {
            if (home!=null){
                fragTransaction.show(home)
            }
        }
        else if (tag == TAG_MAJOR) {
            if (major != null) {
                fragTransaction.show(major)
            }
        }

        else if (tag == TAG_SCHEDULE){
            if (schedule != null){
                fragTransaction.show(schedule)
            }
        }
        else if (tag == TAG_CULTURE){
            if (culture != null){
                fragTransaction.show(culture)
            }
        }
        fragTransaction.commitAllowingStateLoss()
    }
}