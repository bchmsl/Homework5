package com.bchmsl.homework5.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bchmsl.homework5.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        auth = FirebaseAuth.getInstance()
        checkAccount()
        listeners()
        setText()
    }

    private fun setText() {

    }

    private fun listeners() {
        binding.btnLogIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivityStep1::class.java)
            startActivity(intent)
        }
    }

    private fun checkAccount(){
        val currentUser = auth.currentUser
        if(currentUser != null){
            val intent = Intent(this, DiscoverActivity::class.java)
            startActivity(intent)
        }else{
            return
        }

    }
}