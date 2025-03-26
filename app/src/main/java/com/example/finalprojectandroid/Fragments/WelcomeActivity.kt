package com.example.finalprojectandroid.Fragments

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.finalprojectandroid.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE)

        val userName = prefs.getString("USER_NAME", "User") ?: "User"
        binding.welcomeText.text = "Welcome back, $userName!"

        binding.btnContinue.setOnClickListener {
            startActivity(Intent(this, LessonsListActivity::class.java))
        }

        binding.btnReset.setOnClickListener {
            prefs.edit().clear().apply()
            startActivity(Intent(this, EnterNameActivity::class.java))
            finish()
        }
    }
}