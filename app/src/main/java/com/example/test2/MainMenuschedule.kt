package com.example.test2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import androidx.fragment.app.Fragment


class MainMenuschedule : Fragment() {

    lateinit var calendarView: CalendarView
    lateinit var scheduleTextView: TextView
    lateinit var scheduleMap: MutableMap<String, String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_menuschedule, container, false)

        calendarView = view.findViewById(R.id.calendarView)
        scheduleTextView = view.findViewById(R.id.textView2)

        scheduleMap = HashMap()

        scheduleMap["2023/9/1"] = "2023학년도 2학기 학기개시일"
        scheduleMap["2023/9/8"] = "학기개시 7일"
        scheduleMap["2023/9/28"] = "추석연휴(보강실시)"
        scheduleMap["2023/9/29"] = "추석연휴(보강실시)"
        scheduleMap["2023/9/30"] = "추석연휴(보강실시)"


        scheduleMap["2023/10/20"] = "2학기 중간고사"
        scheduleMap["2023/10/21"] = "2학기 중간고사"
        scheduleMap["2023/10/22"] = "2학기 중간고사"
        scheduleMap["2023/10/23"] = "2학기 중간고사/수업일수 1/2"
        scheduleMap["2023/10/24"] = "2학기 중간고사"
        scheduleMap["2023/10/25"] = "2학기 중간고사"
        scheduleMap["2023/10/26"] = "2학기 중간고사"
        scheduleMap["2023/10/30"] = "학기 개시 60일"

        scheduleMap["2023/11/9"] = "수업 일수 2/3"
        scheduleMap["2023/11/29"] = "학기개시 90일"

        scheduleMap["2023/12/22"] = "동계방학/동계절학기 시작"
        scheduleMap["2023/12/25"] = "성탄절"

        scheduleMap["2024/1/1"] = "신정"
        scheduleMap["2024/1/5"] = "예비수강신청"

        scheduleMap["2024/2/14"] = "본수강신청"
        scheduleMap["2024/2/15"] = "본수강신청"
        scheduleMap["2024/2/16"] = "본수강신청/전기 학위수여식"

        scheduleMap["2024/2/26"] = "2024-1학기 등록기간"
        scheduleMap["2024/2/27"] = "2024-1학기 등록기간"
        scheduleMap["2024/2/28"] = "2024-1학기 등록기간"
        scheduleMap["2024/2/29"] = "2024-1학기 등록기간"





        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = "$year/${month + 1}/$dayOfMonth" // 선택한 날짜
            val scheduleForSelectedDate = getScheduleForDate(selectedDate)

            scheduleTextView.text = scheduleForSelectedDate
        }

        return view
    }

    private fun getScheduleForDate(selectedDate: String): String {
        return scheduleMap[selectedDate] ?: "일정 없음"
    }
}