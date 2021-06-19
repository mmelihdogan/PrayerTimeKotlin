package com.melihd.prayertimekotlin.model

data class PrayerTimeModel(
    val id : Int,
    val country : String,
    val city : String,
    val region : String,
    val date : String,
    val fajr : String,
    val sun : String,
    val dhuhr : String,
    val asr : String,
    val maghrib : String,
    val isha : String)

