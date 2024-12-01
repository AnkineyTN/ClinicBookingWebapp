package com.test.clinicbookingapp.utilities

import java.time.LocalDate

data class AvailableDay(
    val date: LocalDate,
    val timeSlot: List<TimeSlot>
) {
}