package com.test.clinicbookingapp.model

import android.net.Uri

class Patient (
    avatar : Uri,
    name : String,
    gender : String,
    address : String,
    dob : String,
    phone : String,

) : People(avatar, name, gender, address, dob, phone) {
}