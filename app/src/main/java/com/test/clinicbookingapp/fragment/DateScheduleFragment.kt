package com.test.clinicbookingapp.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.clinicbookingapp.R
import com.test.clinicbookingapp.adapter.DateAvailableAdapter
import com.test.clinicbookingapp.model.Doctor

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DateScheduleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DateScheduleFragment : Fragment(R.layout.fragment_date_schedule) {
    private var recyclerView : RecyclerView? = null;
    private var recyclerViewAdapter : DateAvailableAdapter? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_date_schedule, container, false);

        val doctor : Doctor? = arguments?.getParcelable("doctor");
        Log.i("argument", arguments.toString());
        recyclerView = view.findViewById(R.id.dateAvailable);
        recyclerViewAdapter = doctor?.schedule?.let { DateAvailableAdapter(it, 0) };
        recyclerView!!.adapter = recyclerViewAdapter;
        recyclerViewAdapter!!.onItemClick = { s ->

        }
        recyclerView!!.layoutManager = GridLayoutManager(requireContext(), 8);


        return view;
    }

}