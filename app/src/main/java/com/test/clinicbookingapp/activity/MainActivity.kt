package com.test.clinicbookingapp.activity

import android.content.Intent
import android.os.Bundle
import retrofit2.Call;
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.test.clinicbookingapp.R
import com.test.clinicbookingapp.RetrofitClient.apiService
import com.test.clinicbookingapp.model.User
import retrofit2.Callback;
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private var bookingBtn : Button? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val apiService = apiService
        val call: Call<User>? = apiService.user

        call?.enqueue(object : Callback<User?> {
            override fun onResponse(call: Call<User?>?, response: Response<User?>) {
                if (response.isSuccessful()) {
                    val user: User? = response.body()
                    Log.i("test", "${user?.name} va ${user?.email}");
                } else {
                    Log.i("error", "error call api");
                }
            }

            override fun onFailure(call: Call<User?>?, t: Throwable) {
                t.message?.let { Log.i("failure", it) };
            }
        })



//        bookingBtn = findViewById(R.id.bookingBtn) as Button;
//
//        bookingBtn?.setOnClickListener{v ->
//            Log.d("BookingButton", "Button clicked!");
//            startActivity(Intent(this, BookingActivity::class.java));
//        }
    }
}