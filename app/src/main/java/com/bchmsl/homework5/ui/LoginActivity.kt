package com.bchmsl.homework5.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import com.bchmsl.homework5.databinding.ActivityLoginBinding
import com.bchmsl.homework5.tools.*
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        auth = FirebaseAuth.getInstance()
        listeners()
    }

    private fun listeners() {
        binding.btnLogin.setOnClickListener {
            login()
        }
        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.etEmail.addTextChangedListener {
            binding.tilEmail.isErrorEnabled = false
        }
        binding.etPassword.addTextChangedListener {
            binding.tilPassword.isErrorEnabled = false
        }
    }

    private fun login() {
        val checker = FieldsChecker()
        val email = Field(FieldType.EMAIL, binding.etEmail.text.toString())
        val password = Field(FieldType.PASSWORD, binding.etPassword.text.toString())
        if (checker.checkFields(email)) {
            if (checker.checkFields(password)) {
                auth.signInWithEmailAndPassword(email.value, password.value)
                    .addOnCompleteListener {
                        firebaseCompleted(it)
                    }
            } else {
                binding.tilPassword.error = "Check Password!"
                binding.tvError.makeError("Password must be at least 8 characters long!", true)
            }
        } else {
            binding.tilEmail.error = "Check Email!"
            binding.tvError.makeError("Email is invalid!", true)
        }
    }


    private fun firebaseCompleted(it: Task<AuthResult>){
        if (it.isSuccessful) {
            val intent = Intent(this, DiscoverActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK



            startActivity(intent)
        }  else {
            val handler = FirebaseExceptionHandler()

            Log.d("TAG-Firebase", it.exception.toString())
            binding.tvError.makeError(handler.handleException(it.exception), true)

        }
    }
}