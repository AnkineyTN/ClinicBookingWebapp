package com.test.clinicbookingapp

import com.test.clinicbookingapp.model.User
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
    @get:GET("users/1")
    val user: Call<User>?
}