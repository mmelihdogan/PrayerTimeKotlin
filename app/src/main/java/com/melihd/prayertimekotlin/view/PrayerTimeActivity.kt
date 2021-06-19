package com.melihd.prayertimekotlin.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.melihd.prayertimekotlin.R
import com.melihd.prayertimekotlin.model.PrayerTimeModel
import com.melihd.prayertimekotlin.service.PrayerTimeAPI
import kotlinx.android.synthetic.main.activity_prayer_time.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PrayerTimeActivity : AppCompatActivity() {

    private val BASE_URL = "https://prayertimes.api.abdus.dev/api/diyanet/"
    private var prayerModels : ArrayList<PrayerTimeModel>? = null

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
        val monthString = intent.getStringExtra("monthString")
        val hijriDay = intent.getIntExtra("hijriDay", 0)
        val hijriMonth = intent.getIntExtra("hijriMonth", 0)
        val hijriYear = intent.getIntExtra("hijriYear", 0)
        val hijriMonthString = intent.getStringExtra("hijriMonthString")

        textCityName.text = city

        var myCurrentDay = "$day $monthString, $hour:$minute"

        if (hour < 10) {
            myCurrentDay = "$day $monthString, 0$hour:$minute"
        } else if (minute < 10) {
            myCurrentDay = "$day $monthString, $hour:0$minute"
        } else if (hour < 10 && minute < 10) {
            myCurrentDay = "$day $monthString, 0$hour:0$minute"
        }

        textDate.text = myCurrentDay

        textHijriDate.text = "$hijriDay $hijriMonthString $hijriYear"

        loadData()

        }

        private fun loadData() {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(PrayerTimeAPI::class.java)
            val call = service.getData()

            call.enqueue(object: Callback<List<PrayerTimeModel>>{
                override fun onResponse(
                    call: Call<List<PrayerTimeModel>>,
                    response: Response<List<PrayerTimeModel>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            prayerModels = ArrayList(it)

                                println(prayerModels!![0].id)
                                println(prayerModels!![0].country)
                                println(prayerModels!![0].city)
                                println(prayerModels!![0].region)
                                println(prayerModels!![0].date)
                                println(prayerModels!![0].fajr)
                                println(prayerModels!![0].sun)
                                println(prayerModels!![0].dhuhr)
                                println(prayerModels!![0].asr)
                                println(prayerModels!![0].maghrib)
                                println(prayerModels!![0].isha)

                        }
                    }
                }

                override fun onFailure(call: Call<List<PrayerTimeModel>>, t: Throwable) {
                    t.printStackTrace()
                }

            })
        }

}


