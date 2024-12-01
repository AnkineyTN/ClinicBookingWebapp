package com.test.clinicbookingapp.mock

import android.net.Uri
import com.test.clinicbookingapp.R
import com.test.clinicbookingapp.model.Doctor
import com.test.clinicbookingapp.utilities.AvailableDay
import com.test.clinicbookingapp.utilities.TimeSlot
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object MockDoctor {
    val timeSlot1 = TimeSlot(LocalTime.of(8, 0), LocalTime.of(10, 0));
    val timeSlot2 = TimeSlot(LocalTime.of(10, 30), LocalTime.of(11, 20));
    val timeSlot3 = TimeSlot(LocalTime.of(14, 0), LocalTime.of(16, 0));
    val timeSlot4 = TimeSlot(LocalTime.of(13, 30), LocalTime.of(14, 0));
    val timeSlot5 = TimeSlot(LocalTime.of(16, 30), LocalTime.of(17, 30));

    val availableDay1 = AvailableDay(LocalDate.of(2024, 11, 1), listOf(timeSlot1, timeSlot2));
    val availableDay2 = AvailableDay(LocalDate.of(2024, 12, 2), listOf(timeSlot3, timeSlot4));
    val availableDay3 = AvailableDay(LocalDate.of(2024, 12, 3), listOf(timeSlot1, timeSlot5));
    val availableDay4 = AvailableDay(LocalDate.of(2024, 12, 10), listOf(timeSlot2));
    val availableDay5 = AvailableDay(LocalDate.of(2024, 12, 31), listOf(timeSlot5));
    val availableDay6 = AvailableDay(LocalDate.of(2024, 12, 20), listOf(timeSlot2, timeSlot3));

    val doctor = Doctor(
        Uri.parse("android.resource://com.test.clinicbookingapp/${R.drawable.avatar}"),
        "Lê Anh Thư",
        "Female",
        "12/12/2002",
        "",
        "0345678932",
        "PGS. TS. BS",
        "Tiêu hóa",
        schedule = listOf(availableDay1, availableDay2, availableDay3, availableDay4, availableDay5, availableDay6)
    );

    val dateAvailableSpinnerList : List<String> = doctor?.schedule?.map {
            availableDate ->
        availableDate.date.format(DateTimeFormatter.ofPattern("MM/yyyy"));
    }?.distinct()?: emptyList();
}