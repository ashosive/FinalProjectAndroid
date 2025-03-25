package com.example.finalprojectandroid.Fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.finalprojectandroid.databinding.FragmentWelcomeBackBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: FragmentWelcomeBackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentWelcomeBackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get saved name
        val prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        val userName = prefs.getString("user_name", "User")

        // Set welcome message
        binding.tvWelcome.text = "Welcome, $userName!"

//        binding.btnContinue.setOnClickListener {
//            // Start LessonsListActivity
//            startActivity(Intent(this, ::class.java))
//        }

//        binding.btnReset.setOnClickListener {
//            // Clear preferences and go back to EnterNameActivity
//            prefs.edit().clear().apply()
//            startActivity(Intent(this, EnterNameActivity::class.java))
//            finish()
//        }
    }
}