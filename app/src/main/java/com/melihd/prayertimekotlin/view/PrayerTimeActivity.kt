package com.melihd.prayertimekotlin.view

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.melihd.prayertimekotlin.R
import com.melihd.prayertimekotlin.adapter.RecyclerViewAdapter
import com.melihd.prayertimekotlin.model.PrayerTimeModel
import com.melihd.prayertimekotlin.service.PrayerTimeAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_prayer_time.*
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PrayerTimeActivity : AppCompatActivity() {

    private val BASE_URL = "https://prayertimes.api.abdus.dev/api/diyanet/"
    private var prayerModels : ArrayList<PrayerTimeModel>? = null

    private var recyclerViewAdapter : RecyclerViewAdapter? = null

    // Disposable
    private var compositeDisposable : CompositeDisposable? = null

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

        var myCurrentDay = "$day $monthString $year, $hour:$minute"

        if (hour < 10) {
            myCurrentDay = "$day $monthString $year, 0$hour:$minute"
        } else if (minute < 10) {
            myCurrentDay = "$day $monthString $year, $hour:0$minute"
        } else if (hour < 10 && minute < 10) {
            myCurrentDay = "$day $monthString $year, 0$hour:0$minute"
        }

        textDate.text = myCurrentDay

        textHijriDate.text = "$hijriDay $hijriMonthString $hijriYear"

        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        compositeDisposable = CompositeDisposable()


        loadData()

        }

        private fun loadData() {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(PrayerTimeAPI::class.java)

            compositeDisposable?.add(retrofit.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse)
            )

            /*
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

                            prayerModels?.let {
                                recyclerViewAdapter = RecyclerViewAdapter(it)
                                recyclerView.adapter = recyclerViewAdapter
                            }


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

            */

        }

    private fun handleResponse(prayerList: List<PrayerTimeModel>) {
        prayerModels = ArrayList(prayerList)

        prayerModels?.let {
            recyclerViewAdapter = RecyclerViewAdapter(it)
            recyclerView.adapter = recyclerViewAdapter
        }

        println(prayerModels!![0].date)
        println(prayerModels!![0].fajr)
        println(prayerModels!![0].sun)
        println(prayerModels!![0].dhuhr)
        println(prayerModels!![0].asr)
        println(prayerModels!![0].maghrib)
        println(prayerModels!![0].isha)

    }
}


