package com.melihd.prayertimekotlin.service

import com.melihd.prayertimekotlin.model.PrayerTimeModel
import retrofit2.Call
import retrofit2.http.GET

interface PrayerTimeAPI {

    // https://prayertimes.api.abdus.dev/api/diyanet/search?q=istanbul
    // https://prayertimes.api.abdus.dev/api/diyanet/prayertimes?location_id=9541

    @GET("search?q=")
    fun getData() : Call<List<PrayerTimeModel>>
}