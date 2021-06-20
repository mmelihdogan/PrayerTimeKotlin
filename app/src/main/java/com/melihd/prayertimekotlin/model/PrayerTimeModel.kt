package com.melihd.prayertimekotlin.model

data class PrayerTimeModel(
    val date : String,
    val fajr : String,
    val sun : String,
    val dhuhr : String,
    val asr : String,
    val maghrib : String,
    val isha : String
    )

