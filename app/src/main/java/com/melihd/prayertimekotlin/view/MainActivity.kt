package com.melihd.prayertimekotlin.view

import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import androidx.annotation.RequiresApi
import com.melihd.prayertimekotlin.R.layout.activity_main
import com.melihd.prayertimekotlin.model.PrayerTimeModel
import com.melihd.prayertimekotlin.service.PrayerTimeAPI
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun save(view : View) {

        // SharedPreferences
        val sharedPreferences = this.getSharedPreferences("com.melihd.prayertimekotlin", Context.MODE_PRIVATE)

        val myCity = getCityName.text.toString()

        println("This is your city: " + myCity)
        sharedPreferences.edit().putString("city", myCity).apply()

        // Get the current time and date
        val c = Calendar.getInstance()

        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        println("TIME IS: $day $month $year $hour $minute")
        sharedPreferences.edit().putInt("year", year).apply()
        sharedPreferences.edit().putInt("month", month).apply()
        sharedPreferences.edit().putInt("day", day).apply()
        sharedPreferences.edit().putInt("hour", hour).apply()
        sharedPreferences.edit().putInt("minute", minute).apply()

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

        val intent = Intent(applicationContext, PrayerTimeActivity::class.java)
        intent.putExtra("city", myCity)
        intent.putExtra("day", day)
        intent.putExtra("month", month)
        intent.putExtra("year", year)
        intent.putExtra("hour", hour)
        intent.putExtra("minute", minute)
        intent.putExtra("monthString", monthString)
        startActivity(intent)
        finish()

    }
}