package com.test.clinicbookingapp.activity

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.clinicbookingapp.R
import com.test.clinicbookingapp.adapter.DateAvailableAdapter
import com.test.clinicbookingapp.adapter.TimeSlotAdapter
import com.test.clinicbookingapp.mock.MockDoctor
import com.test.clinicbookingapp.mock.MockPatient
import com.test.clinicbookingapp.model.Doctor
import com.test.clinicbookingapp.model.Patient
import com.test.clinicbookingapp.utilities.AvailableDay
import com.test.clinicbookingapp.utilities.TimeSlot
import java.time.Month

class BookingActivity : AppCompatActivity() {

    private var doctor : Doctor? = null;
    private var patient : Patient? = null;
    private var availableDayRecyclerViewAdapter : DateAvailableAdapter? = null;
    private var timeslotRecyclerViewAdapter : TimeSlotAdapter? = null;
    private var isMorningTimeSlot = true;
    private var dateAvailableDays : List<AvailableDay> = mutableListOf();
    private var timeslots: List<TimeSlot> = mutableListOf();

    private var avatarIV : ImageView? = null;
    private var degreeTV : TextView? = null;
    private var nameTV : TextView? = null;
    private var majorTV : TextView? = null;

    private var patientNameTV : TextView? = null;
    private var patientGenderTV : TextView? = null;
    private var patientDobTV : TextView? = null;
    private var patientPhoneTV : TextView? = null;

    private var dateSpinner : Spinner? = null;
    private var availableDayRecyclerView : RecyclerView? = null;
    private var morningBtn : Button? = null;
    private var eveningBtn : Button? = null;
    private var timeslotRecyclerView : RecyclerView? = null;

    private var continueBtn : Button? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        doctor = MockDoctor.doctor;
        patient = MockPatient.patient;



        avatarIV = findViewById(R.id.avatarIV) as ImageView;
        degreeTV = findViewById(R.id.doctorDegree) as TextView;
        nameTV = findViewById(R.id.doctorName) as TextView;
        majorTV = findViewById(R.id.doctorMajor) as TextView;

        patientNameTV = findViewById(R.id.patientName) as TextView;
        patientGenderTV = findViewById(R.id.patientGender) as TextView;
        patientDobTV = findViewById(R.id.patientDob) as TextView;
        patientPhoneTV = findViewById(R.id.patientPhone) as TextView;

        dateSpinner = findViewById(R.id.dateSpinner) as Spinner;

        avatarIV?.setImageURI(doctor!!.avatar);
        degreeTV?.setText(doctor!!.degree);
        nameTV?.setText(doctor!!.name);
        majorTV?.setText(doctor!!.major);

        patientNameTV?.setText(patient!!.name);
        patientGenderTV?.setText(patient!!.gender);
        patientDobTV?.setText(patient!!.dob);
        patientPhoneTV?.setText(patient!!.phone);

        ArrayAdapter(this, android.R.layout.simple_spinner_item, MockDoctor.dateAvailableSpinnerList)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dateSpinner!!.adapter = adapter;
            };
        dateSpinner?.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedDate = parent.getItemAtPosition(position).toString();
                val (month, year) = handleSelectedDate(selectedDate);
                dateAvailableDays = getSchedule(month, year);
                availableDayRecyclerViewAdapter?.updateListDates(dateAvailableDays);
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        })

        availableDayRecyclerView = findViewById(R.id.dateRecyclerView) as RecyclerView;
        availableDayRecyclerViewAdapter = doctor?.schedule?.let { DateAvailableAdapter(it, 1) };
        availableDayRecyclerView!!.adapter = availableDayRecyclerViewAdapter;
        availableDayRecyclerViewAdapter!!.onItemClick = { availableDay ->
            timeslots = availableDay.timeSlot;
            var filteredTimeslot : List<TimeSlot> = mutableListOf();
            if (isMorningTimeSlot) {
                filteredTimeslot = filterTime("morning");
            } else {
                filteredTimeslot = filterTime("evening");
            }
            timeslotRecyclerViewAdapter?.updateListTimeslots(filteredTimeslot);
        }
        availableDayRecyclerView!!.layoutManager = GridLayoutManager(this, 4);


        morningBtn = findViewById(R.id.morningBtn) as Button;
        eveningBtn = findViewById(R.id.eveningBtn) as Button;
        morningBtn?.setOnClickListener{ v ->
            changeTimeslotBtn("morning");
            var morningTimeslot = filterTime("morning");
            timeslotRecyclerViewAdapter?.updateListTimeslots(morningTimeslot);
        }
        eveningBtn?.setOnClickListener{ v ->
            changeTimeslotBtn("evening");
            var eveningTimeslot = filterTime("evening");
            timeslotRecyclerViewAdapter?.updateListTimeslots(eveningTimeslot);
        }

        timeslotRecyclerView = findViewById(R.id.timeslotRecyclerView) as RecyclerView;
        timeslotRecyclerViewAdapter = TimeSlotAdapter(timeslots, 0);
        timeslotRecyclerView!!.adapter = timeslotRecyclerViewAdapter;
        timeslotRecyclerViewAdapter!!.onItemClick = {timeslot ->

        }
        timeslotRecyclerView!!.layoutManager = GridLayoutManager(this, 3);


        continueBtn = findViewById(R.id.continueBtn) as Button;
        continueBtn?.setOnClickListener { v ->
            startActivity(Intent(this, ConfirmBookingActivity::class.java));
        }
    }

    fun changeTimeslotBtn(type : String) {
        if (type == "morning") {
            if (!isMorningTimeSlot) {
                morningBtn?.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#214EF3"));
                eveningBtn?.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#C4AEE9"));
                isMorningTimeSlot = !isMorningTimeSlot;
            }
        }
        if (type == "evening") {
            if (isMorningTimeSlot) {
                morningBtn?.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#C4AEE9"));
                eveningBtn?.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#214EF3"));
                isMorningTimeSlot = !isMorningTimeSlot;
            }
        }

    }

    fun getSchedule(month : Int, year : Int) : List<AvailableDay> {
        val availableDays = doctor?.schedule?.filter { availableDay ->
            availableDay.date.month == Month.of(month) && availableDay.date.year == year
        }?.toList() ?: emptyList();
        return availableDays;
    }

    fun handleSelectedDate(selectedDate: String) : Pair<Int, Int> {
        val splitItems = selectedDate.split('/');
        return Pair(splitItems[0].toInt(), splitItems[1].toInt());
    }

    fun filterTime(type : String) : List<TimeSlot> {
        if (type == "morning") {
            return timeslots.filter { timeslot ->
                timeslot.endTime.hour <= 11 && timeslot.endTime.minute <= 30;
            }
        }
        if (type == "evening") {
            return timeslots.filter { timeslot ->
                timeslot.startTime.hour > 12;
            }
        }
        return emptyList();
    }
}