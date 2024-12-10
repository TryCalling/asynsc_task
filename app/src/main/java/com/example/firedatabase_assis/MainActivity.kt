package com.example.firedatabase_assis

import android.content.ContentValues
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.firedatabase_assis.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set status bar color programmatically
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = resources.getColor(R.color.teal_700, theme)
        }

        // Database setup
        val dbhelp = DB_class(applicationContext)
        val db = dbhelp.writableDatabase

        // Registration button logic
        binding.btnrgs.setOnClickListener {
            val name = binding.ed1.text.toString()
            val username = binding.ed2.text.toString()
            val password = binding.ed3.text.toString()

            if (name.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty()) {
                val data = ContentValues()
                data.put("name", name)
                data.put("username", username)
                data.put("pswd", password)

                val rs: Long = db.insert("user", null, data)
                if (rs != -1L) {
                    val ad = AlertDialog.Builder(this)
                    ad.setTitle("Message")
                    ad.setMessage("Account registered successfully")
                    ad.setPositiveButton("Ok", null)
                    ad.show()
                    binding.ed1.text.clear()
                    binding.ed2.text.clear()
                    binding.ed3.text.clear()
                } else {
                    val ad = AlertDialog.Builder(this)
                    ad.setTitle("Message")
                    ad.setMessage("Record not added")
                    ad.setPositiveButton("Ok", null)
                    ad.show()
                    binding.ed1.text.clear()
                    binding.ed2.text.clear()
                    binding.ed3.text.clear()
                }
            } else {
                Toast.makeText(this, "All fields required", Toast.LENGTH_SHORT).show()
            }
        }

        // Login link logic
        binding.loginLink.setOnClickListener {
            val intent = Intent(this, login_form::class.java)
            startActivity(intent)
        }
    }
}
