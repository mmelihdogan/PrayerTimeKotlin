package com.melihd.prayertimekotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.melihd.prayertimekotlin.R
import com.melihd.prayertimekotlin.model.PrayerTimeModel
import kotlinx.android.synthetic.main.activity_prayer_time.*
import kotlinx.android.synthetic.main.row_layout.view.*

class RecyclerViewAdapter(private val prayerTimeList : ArrayList<PrayerTimeModel>) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {


    class RowHolder(view : View) : RecyclerView.ViewHolder(view) {

        fun bind(prayerTimeModel: PrayerTimeModel) {
            itemView.text_time.text = prayerTimeModel.fajr
            itemView.text_time1.text = prayerTimeModel.sun
            itemView.text_time2.text = prayerTimeModel.dhuhr
            itemView.text_time3.text = prayerTimeModel.asr
            itemView.text_time4.text = prayerTimeModel.maghrib
            itemView.text_time5.text = prayerTimeModel.isha

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(prayerTimeList[0])
    }

    override fun getItemCount(): Int {

        var count = prayerTimeList.count()

        if (count > 1) {
            return 1
        } else {
            return prayerTimeList.count()
        }

    }
}