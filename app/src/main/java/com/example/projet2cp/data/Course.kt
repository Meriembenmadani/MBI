package com.example.projet2cp.data

data class Course(
    val name : String,
    val date : String,
    val img : Int,
    val lessons : Int,
    val places:Int,
    val price : Float,
    var language: String = "",
    var level: String= ""

    )

