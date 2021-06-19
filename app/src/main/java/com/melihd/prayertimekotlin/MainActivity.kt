package com.melihd.prayertimekotlin

import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import androidx.annotation.RequiresApi
import com.melihd.prayertimekotlin.R.layout.activity_main
import kotlinx.android.synthetic.main.activity_main.*

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

        if (myCity != null) {
            println("This is your city: " + myCity)
            sharedPreferences.edit().putString("city", myCity).apply()
        }

        // Get the current time and date
        val c = Calendar.getInstance()

        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

//        println("$day $month $year $hour $minute")

        val intent = Intent(applicationContext, PrayerTimeActivity::class.java)
        intent.putExtra("city", myCity)
        intent.putExtra("day", day)
        intent.putExtra("month", month)
        intent.putExtra("year", year)
        intent.putExtra("hour", hour)
        intent.putExtra("minute", minute)
        startActivity(intent)
//        finish()
    }
}