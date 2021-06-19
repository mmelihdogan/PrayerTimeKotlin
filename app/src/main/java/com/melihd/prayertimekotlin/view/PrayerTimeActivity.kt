package com.melihd.prayertimekotlin.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.melihd.prayertimekotlin.R
import kotlinx.android.synthetic.main.activity_prayer_time.*

class PrayerTimeActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prayer_time)

        // getIntent
        val intent = intent
        val city = intent.getStringExtra("city")
        val day = intent.getIntExtra("day", 0)
        val month = intent.getIntExtra("month", 0)
        val year = intent.getIntExtra("year", 0)
        val hour = intent.getIntExtra("hour", 0)
        val minute = intent.getIntExtra("minute", 0)

        textCityName.text = city

        val MONTHS = arrayListOf<String>(
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December")

        val monthString = MONTHS[month-1]

        var myCurrentDay = "$day $monthString, $hour:$minute"

        if (hour < 10) {
            myCurrentDay = "$day $monthString, 0$hour:$minute"
        } else if (minute < 10) {
            myCurrentDay = "$day $monthString, $hour:0$minute"
        } else if (hour < 10 && minute < 10) {
            myCurrentDay = "$day $monthString, 0$hour:0$minute"
        }

        textDate.text = myCurrentDay



    }
}