package com.example.test2

data class Subject(
    var completed: Boolean =false, // 체크박스 여부
    val title: String, // 과목이름
    val score :Int =0, //학점
    var MSC : Double=0.0, // MSC
    var Design : Double = 0.0 // 설계학점
)
data class Subject2(
    var completed2: Boolean =false,
    val title2: String,
    val score2 :Int =0 //교양학점
)