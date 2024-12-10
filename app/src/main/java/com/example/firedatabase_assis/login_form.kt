package com.example.firedatabase_assis

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.firedatabase_assis.databinding.ActivityLoginFormBinding

class login_form : AppCompatActivity() {
    private lateinit var bind: ActivityLoginFormBinding

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityLoginFormBinding.inflate(layoutInflater)
        setContentView(bind.root)

        // Set status bar color programmatically
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = resources.getColor(R.color.teal_700, theme)
        }

        // Database setup
        val dbhelp = DB_class(applicationContext)
        val db = dbhelp.readableDatabase

        // Login button logic
        bind.btnlogin.setOnClickListener {
            val username = bind.logtxt.text.toString()
            val password = bind.ed3.text.toString()

            // Validate input
            if (username.isNotEmpty() && password.isNotEmpty()) {
                val query =
                    "SELECT * FROM user WHERE username='$username' AND pswd='$password'"
                val rs = db.rawQuery(query, null)

                if (rs.moveToFirst()) {
                    val name = rs.getString(rs.getColumnIndex("name"))
                    rs.close()

                    // Navigate to the welcome window
                    val intent = Intent(this, welcome_window::class.java)
                    intent.putExtra("name", name)
                    startActivity(intent)
                } else {
                    val ad = AlertDialog.Builder(this)
                    ad.setTitle("Message")
                    ad.setMessage("Username or password is incorrect!")
                    ad.setPositiveButton("Ok", null)
                    ad.show()
                }
            } else {
                val ad = AlertDialog.Builder(this)
                ad.setTitle("Input Required")
                ad.setMessage("Please fill in both fields.")
                ad.setPositiveButton("Ok", null)
                ad.show()
            }
        }

        // Registration link logic
        bind.regisLink.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
