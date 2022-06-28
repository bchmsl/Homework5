package com.bchmsl.homework5.ui.main

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.bchmsl.homework5.databinding.ActivityMainBinding
import com.bchmsl.homework5.ui.discover.DiscoverActivity
import com.bchmsl.homework5.ui.login.LoginActivity
import com.bchmsl.homework5.ui.register.RegisterActivityStep1
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


val Context.dataStore by preferencesDataStore(name = "EMAIL")

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        auth = FirebaseAuth.getInstance()
        checkAccount()
        listeners()
        setUsername()
    }

    private fun setUsername() {
        lifecycleScope.launch {
            val email = getEmail() ?: ""
            binding.tvEmail.text = email

            if (email.isEmpty()) {
                binding.tvEmail.visibility = View.INVISIBLE
                binding.tvLastSignedIn.visibility = View.INVISIBLE
            } else {
                binding.tvEmail.visibility = View.VISIBLE
                binding.tvLastSignedIn.visibility = View.VISIBLE
            }
        }
    }

    private suspend fun getEmail(): String? {
        val dataStoreKey = stringPreferencesKey("email")
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
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

    private fun checkAccount() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, DiscoverActivity::class.java)
            startActivity(intent)
        } else {
            return
        }
    }
}