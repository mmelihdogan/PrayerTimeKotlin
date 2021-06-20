package com.melihd.prayertimekotlin.service

import com.melihd.prayertimekotlin.model.PrayerTimeModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface PrayerTimeAPI {

    // https://prayertimes.api.abdus.dev/api/diyanet/search?q=istanbul
    // https://prayertimes.api.abdus.dev/api/diyanet/prayertimes?location_id=9541

    @GET("prayertimes?location_id=9541")
    fun getData() : Observable<List<PrayerTimeModel>>

//     fun getData() : Call<List<PrayerTimeModel>>
}