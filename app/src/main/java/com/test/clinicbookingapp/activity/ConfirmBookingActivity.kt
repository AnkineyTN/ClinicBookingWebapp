package com.test.clinicbookingapp.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.test.clinicbookingapp.R

class ConfirmBookingActivity : AppCompatActivity() {

    private var confirmBtn : Button? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_confirm_booking)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        confirmBtn = findViewById(R.id.confirmBtn) as Button;
        confirmBtn?.setOnClickListener{ v ->
            startActivity(Intent(this, ResultBookingActivity::class.java));
        }
    }
}