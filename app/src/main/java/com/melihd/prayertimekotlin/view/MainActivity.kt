package com.melihd.prayertimekotlin.view

import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.icu.util.IslamicCalendar
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.melihd.prayertimekotlin.R.layout.activity_main
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import java.time.chrono.HijrahDate

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

//        val localDate: LocalDate = LocalDate.of(year, month, day);
//        val hijrahDate: HijrahDate = HijrahDate.from(localDate)

        val hijriDateString = HijrahDate.now().toString().split(" ").toTypedArray()

        println("Hijri: " + HijrahDate.now())

        val hijriDate = hijriDateString[2].split("-").toTypedArray()

//        for (item in hijriDate) {
//            println(item)
//        }

        val hijriYear = hijriDate[0].toInt()
        val hijriMonth = hijriDate[1].toInt()
        val hijriDay = hijriDate[2].toInt()


        val HIJRI_MONTHS = arrayListOf<String>(
            "Muharrem",
            "Safer",
            "Rebiülevvel",
            "Rebiülahir",
            "Cemaziyelevvel",
            "Cemaziyelahir",
            "Recep",
            "Şaban",
            "Ramazan",
            "Şevval",
            "Zilkade",
            "Zilhicce")

        val hijriMonthString = HIJRI_MONTHS[hijriMonth-1]


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
        intent.putExtra("hijriYear", hijriYear)
        intent.putExtra("hijriMonth", hijriMonth)
        intent.putExtra("hijriDay", hijriDay)
        intent.putExtra("hijriMonthString", hijriMonthString)

        startActivity(intent)
        finish()

    }
}