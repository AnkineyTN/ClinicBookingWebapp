package com.test.clinicbookingapp.model

import android.net.Uri
import com.test.clinicbookingapp.utilities.AvailableDay
import java.util.Date

class Doctor (
    avatar : Uri,
    name : String,
    gender : String,
    dob : String,
    address : String,
    phone : String,
    var degree : String?,
    var major : String?,
    var schedule : List<AvailableDay>
) : People(avatar, name, gender, dob, address, phone) {

}