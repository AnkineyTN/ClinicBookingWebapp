package com.test.clinicbookingapp.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.clinicbookingapp.R
import com.test.clinicbookingapp.utilities.AvailableDay
import com.test.clinicbookingapp.utilities.TimeSlot
import java.time.format.DateTimeFormatter

class TimeSlotAdapter(private var timeslots : List<TimeSlot>, private val layoutType: Int) : RecyclerView.Adapter<TimeSlotAdapter.ViewHolder>() {

    var onItemClick: ((TimeSlot) -> Unit)? = null;

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {

        val timeslotTV = listItemView.findViewById<TextView>(R.id.timeslotTV);

        init {

            timeslotTV.setOnClickListener { v ->
                val currentTint = (timeslotTV.backgroundTintList?.defaultColor) ?: Color.TRANSPARENT;

                if (currentTint == Color.parseColor("#ffffff")) {
                    timeslotTV.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#20BF20"));
                    timeslotTV.setTextColor(Color.parseColor("#ffffff"));
                } else {
                    timeslotTV.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#ffffff"));
                    timeslotTV.setTextColor(Color.parseColor("#000000"));
                }
            }


            listItemView.setOnClickListener {
                Log.i("test", timeslots.get(adapterPosition).toString());
                onItemClick?.invoke(timeslots.get(adapterPosition));
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context;
        val inflater = LayoutInflater.from(context);
        val dateRowView = inflater.inflate(R.layout.custom_timeslot_item, parent, false);
        return ViewHolder(dateRowView);
    }

    override fun getItemCount(): Int {
        return timeslots.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val timeslot = timeslots.get(position);
        val timeslotTV = holder.timeslotTV;
        val timeString : String = timeslot.startTime.format(DateTimeFormatter.ofPattern("hh:mm")) + " - " + timeslot.endTime.format(DateTimeFormatter.ofPattern("hh:mm"))
        timeslotTV.text = timeString;
    }

    fun updateListTimeslots(timeslots: List<TimeSlot>) {
        this.timeslots = timeslots;
        notifyDataSetChanged();
    }

}