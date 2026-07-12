package com.example.technicalsumm2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.TextView

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // ========================================
        // UI COMPONENTS
        // ========================================

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etFullName = findViewById<EditText>(R.id.etFullName)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)

        val btRegister = findViewById<Button>(R.id.btRegister)
        val tvAdminLogin = findViewById<TextView>(R.id.tvRegisterPage5)

        // ========================================
        // REGISTER BUTTON
        // ========================================

        btRegister.setOnClickListener {

            val email = etEmail.text.toString().trim()
            val fullName = etFullName.text.toString().trim()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            // Check if any field is empty
            if (email.isEmpty() ||
                fullName.isEmpty() ||
                password.isEmpty() ||
                confirmPassword.isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Please fill in all fields.",
                    Toast.LENGTH_SHORT
                ).show()

            }
            // Check if passwords match
            else if (password != confirmPassword) {

                Toast.makeText(
                    this,
                    "Passwords do not match.",
                    Toast.LENGTH_SHORT
                ).show()

            }
            // Registration successful
            else {

                Toast.makeText(
                    this,
                    "Registration Successful!",
                    Toast.LENGTH_SHORT
                ).show()

                // TODO:
                // Save the user to SQLite or Firebase here.
                // Then navigate to the next screen if desired.
            }
        }
        tvAdminLogin.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }
    }
}