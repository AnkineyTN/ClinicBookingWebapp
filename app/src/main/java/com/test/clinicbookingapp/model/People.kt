package com.test.clinicbookingapp.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
open class People (
    var avatar: Uri?,
    var name: String?,
    var gender: String?,
    var dob: String?,
    var address: String?,
    var phone: String?
) : Parcelable {
}