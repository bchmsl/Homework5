package com.bchmsl.homework5.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.bchmsl.homework5.tools.Field
import com.bchmsl.homework5.tools.FieldType
import com.bchmsl.homework5.tools.FieldsChecker
import com.bchmsl.homework5.databinding.ActivityRegisterStep2Binding

class RegisterActivityStep2 : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterStep2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterStep2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        listeners()
    }

    private fun listeners() {
        binding.btnSignup.setOnClickListener {
            signUp()
        }
        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.etUsername.addTextChangedListener {
            binding.tilUsername.isErrorEnabled = false
        }
    }

    private fun signUp() {
        val checker = FieldsChecker()
        val username = Field(FieldType.USERNAME, binding.etUsername.text.toString())
        if (checker.checkFields(username)) {
            val intent = Intent(this, DiscoverActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("username", binding.etUsername.text.toString())



            startActivity(intent)
        } else {
            binding.tilUsername.error = "Check username!"
        }
    }
}