package com.melihd.prayertimekotlin.model

data class PrayerTimeModel(val id : Int,
                           val city : String,
                           val country : String,
                           val region : String,
                           val date : String,
                           val fajr : String,
                           val sun : String,
                           val dhuhr : String,
                           val asr : String,
                           val maghrib : String,
                           val isha : String)

