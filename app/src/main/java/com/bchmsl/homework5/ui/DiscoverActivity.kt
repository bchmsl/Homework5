package com.bchmsl.homework5.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bchmsl.homework5.databinding.ActivityDiscoverBinding
import com.google.firebase.auth.FirebaseAuth

class DiscoverActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityDiscoverBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiscoverBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init(){
        auth = FirebaseAuth.getInstance()
        setText()
        listeners()
    }

    private fun listeners() {
        binding.btnLogOut.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }

    private fun setText() {
        binding.tvEmail.text = auth.currentUser?.email
        binding.tvUsername.text = intent.getStringExtra("username")
    }
}