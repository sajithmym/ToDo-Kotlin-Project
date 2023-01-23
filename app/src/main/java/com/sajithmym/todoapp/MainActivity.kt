package com.sajithmym.todoapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    // Define Varibles
    lateinit var name : EditText
    lateinit var code : EditText
    lateinit var log : Button
    lateinit var title : TextView
    lateinit var First_page : ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Assign To Varibale Using Id
        name = findViewById(R.id.username)
        code = findViewById(R.id.passcode)
        log = findViewById(R.id.logbutton)
        title = findViewById(R.id.heading)
        First_page = findViewById(R.id.login_page)

        log.setOnClickListener {
            title.text = "Welcome To My App" // Change the title Welcome as  Welcome To My App
            var Name : String = name.text.toString() // Gat Username
            var Code : String = code.text.toString() // Get Passcode
            if (Name == "sajith" && Code == "1020") { // Checking Username, Passcode is Valid
                var App = Intent(this@MainActivity, App::class.java) // Set the Second Activity
                name.setText("")
                code.setText("")
                startActivity(App) // Open App Window
            }else{
                // Show Toast Message
                    Toast.makeText(applicationContext,"Invaid Try Again...",Toast.LENGTH_SHORT).show()
                // Show SnackBar Message
                    Snackbar.make(First_page,"Try Agin With Correct Username and Passcode...",Snackbar.LENGTH_INDEFINITE)
                        .setAction("Close", View.OnClickListener {  })
                        .show()
            }
        }
    }
}