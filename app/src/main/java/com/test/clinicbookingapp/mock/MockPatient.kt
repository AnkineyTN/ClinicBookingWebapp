package com.test.clinicbookingapp.mock

import android.net.Uri
import com.test.clinicbookingapp.R
import com.test.clinicbookingapp.model.Patient

object MockPatient {
    val patient = Patient(
        Uri.parse("android.resource://com.test.clinicbookingapp/${R.drawable.avatar}"),
        "Lê Ái Thi",
        "Female",
        "12/12/2002",
        "",
        "0345678932",
    );
}