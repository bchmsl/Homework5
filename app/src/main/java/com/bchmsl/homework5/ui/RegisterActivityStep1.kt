package com.bchmsl.homework5.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.core.widget.addTextChangedListener
import com.bchmsl.homework5.R
import com.bchmsl.homework5.databinding.ActivityRegisterStep1Binding
import com.bchmsl.homework5.tools.*
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class RegisterActivityStep1 : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegisterStep1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterStep1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        auth = FirebaseAuth.getInstance()
        listeners()
    }

    private fun listeners() {
        binding.btnNext.setOnClickListener {
            signUp()
        }
        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.etEmail.addTextChangedListener {
            binding.tvError.makeError(enabled = false)
            binding.tilEmail.isErrorEnabled = false

        }
        binding.etPassword.addTextChangedListener {
            binding.tvError.makeError(enabled = false)
            binding.tilPassword.isErrorEnabled = false
        }
    }

    private fun signUp() {
        val checker = FieldsChecker()
        val email = Field(FieldType.EMAIL, binding.etEmail.text.toString())
        val password = Field(FieldType.PASSWORD, binding.etPassword.text.toString())

        if (checker.checkFields(email)) {
            if (checker.checkFields(password)) {

                auth.createUserWithEmailAndPassword(email.value, password.value)
                    .addOnCompleteListener {

                        if (it.isSuccessful) {
                            val intent = Intent(this, RegisterActivityStep2::class.java)
                            startActivity(intent)
                        } else {
                            val handler = FirebaseExceptionHandler()

                            d("TAG-Firebase", it.exception.toString())
                            binding.tvError.makeError(handler.handleException(it.exception), true)
                        }
                    }
            } else {
                binding.tilPassword.error = "Check Password!"
                binding.tvError.makeError("Password must be at least 8 characters long!", true)
            }
        } else {
            binding.tvError.makeError("Email is invalid!", true)
            binding.tilEmail.error = "Check Email!"
        }
    }
}