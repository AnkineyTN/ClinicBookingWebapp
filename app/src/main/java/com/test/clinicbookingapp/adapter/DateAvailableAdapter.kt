package com.test.clinicbookingapp.adapter

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

class DateAvailableAdapter(private var dates : List<AvailableDay>, private val layoutType: Int) : RecyclerView.Adapter<DateAvailableAdapter.ViewHolder>() {

    var onItemClick: ((AvailableDay) -> Unit)? = null;
    private var selectedPosition : Int = -1;

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {

        val dayOfWeekTV = listItemView.findViewById<TextView>(R.id.dayOfWeekTV);
        val dateTV = listItemView.findViewById<TextView>(R.id.dateTV)
        val slotsTV = listItemView.findViewById<TextView>(R.id.slotsTV);

        init {
            listItemView.setOnClickListener {
                toggleClick(adapterPosition);
                onItemClick?.invoke(dates.get(adapterPosition));
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context;
        val inflater = LayoutInflater.from(context);
        val dateRowView = inflater.inflate(R.layout.custom_availabledate_item, parent, false);
        return ViewHolder(dateRowView);
    }

    override fun getItemCount(): Int {
        return dates.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date = dates.get(position);
        val dayOfWeekTV = holder.dayOfWeekTV;
        val dateTV = holder.dateTV;
        val slotsTV = holder.slotsTV;
        dayOfWeekTV.text = "T" + (date.date.dayOfWeek.value + 1).toString();
        dateTV.text = date.date.dayOfMonth.toString();
        slotsTV.text = "23 slots";

        if (selectedPosition == position) {
            dateTV.setBackgroundColor(Color.parseColor("#1191ed"));
            dateTV.setTextColor(Color.parseColor("#ffffff"));
        } else {
            dateTV.setBackgroundColor(Color.parseColor("#CDC5DC"));
            dateTV.setTextColor(Color.parseColor("#5E1CCC"));
        }
    }

    fun updateListDates(dates : List<AvailableDay>) {
        this.dates = dates;
        notifyDataSetChanged();
    }

    fun toggleClick(position : Int) {
        val prevSelectedPosition : Int = selectedPosition;
        if (selectedPosition == position) {
            selectedPosition = -1;
        } else {
            selectedPosition = position;
        }
        notifyItemChanged(prevSelectedPosition);
        notifyItemChanged(position);
    }

}