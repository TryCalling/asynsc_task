package com.example.firedatabase_assis

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firedatabase_assis.databinding.ActivityWelcomeWindowBinding

class welcome_window : AppCompatActivity() {
    private lateinit var bind: ActivityWelcomeWindowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityWelcomeWindowBinding.inflate(layoutInflater)
        setContentView(bind.root)

        // Change status bar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = resources.getColor(R.color.teal_700)
        }

        // Receive data and set it to the text view
        val value = intent.getStringExtra("name")
        bind.uname.text = value

        // Logout button listener
        bind.logout.setOnClickListener {
            startActivity(Intent(this, login_form::class.java))
        }
    }
}
