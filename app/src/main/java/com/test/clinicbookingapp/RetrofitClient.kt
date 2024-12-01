package com.test.clinicbookingapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private var retrofit: Retrofit? = null
    private const val BASE_URL =
        "http://10.0.2.2:3000/";

    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

    val apiService: ApiService
        get() = retrofitInstance!!.create(ApiService::class.java)
}