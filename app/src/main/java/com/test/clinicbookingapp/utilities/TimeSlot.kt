package com.test.clinicbookingapp.utilities

import java.time.LocalTime

data class TimeSlot(
    val startTime: LocalTime,
    val endTime: LocalTime
) {
}