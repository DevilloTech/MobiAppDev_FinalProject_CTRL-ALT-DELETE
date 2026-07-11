package com.example.technicalsumm2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RegistrationFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout FIRST
        val view = inflater.inflate(R.layout.fragment_registration, container, false)

        // Connect the EditTexts
        val etEmail = view.findViewById<EditText>(R.id.etEmail)
        val etFullName = view.findViewById<EditText>(R.id.etFullName)
        val etPassword = view.findViewById<EditText>(R.id.etPassword)
        val etConfirmPassword = view.findViewById<EditText>(R.id.etConfirmPassword)

        // Connect the button
        val btRegister = view.findViewById<Button>(R.id.btRegister)

        btRegister.setOnClickListener {

            val email = etEmail.text.toString().trim()
            val fullName = etFullName.text.toString().trim()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            // Check if any field is empty
            if (email.isEmpty() ||
                fullName.isEmpty() ||
                password.isEmpty() ||
                confirmPassword.isEmpty()) {

                Toast.makeText(
                    requireContext(),
                    "Please fill in all fields.",
                    Toast.LENGTH_SHORT
                ).show()

            }
            // Check if passwords match
            else if (password != confirmPassword) {

                Toast.makeText(
                    requireContext(),
                    "Passwords do not match.",
                    Toast.LENGTH_SHORT
                ).show()

            }
            // Registration successful
            else {

                Toast.makeText(
                    requireContext(),
                    "Registration Successful!",
                    Toast.LENGTH_SHORT
                ).show()

            }
        }

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegistrationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}