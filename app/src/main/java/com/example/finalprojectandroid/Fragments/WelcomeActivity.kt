package com.example.finalprojectandroid.Fragments

import com.example.finalprojectandroid.PrefsHelper
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.finalprojectandroid.PrefsHelper.Companion.KEY_COMPLETED_LESSONS
import com.example.finalprojectandroid.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        setupUI()
    }

    override fun onResume() {
        super.onResume()
        updateProgressStats()
    }
    
    private fun setupUI() {
        val userName = prefs.getString("USER_NAME", "User") ?: "User"
        binding.welcomeText.text = "Welcome back, $userName!"

        updateProgressStats()
        val json = prefs.getString(KEY_COMPLETED_LESSONS, null)
            Log.d("***", "JSON: $json")

        binding.btnContinue.setOnClickListener {
            startActivity(Intent(this, LessonsListActivity::class.java))
        }

        binding.btnReset.setOnClickListener {
            prefs.edit().clear().apply()
            startActivity(Intent(this, EnterNameActivity::class.java))
            finish()
        }
    }

    private fun updateProgressStats() {
        val completedIds = prefs.getStringSet("COMPLETED_LESSONS", mutableSetOf()) ?: mutableSetOf()
        val pending = PrefsHelper.TOTAL_LESSONS - completedIds.size
        val percentage = (completedIds.size * 100) / PrefsHelper.TOTAL_LESSONS

       binding.progressStats.text ="""
            Your Progress:
            ✅ ${completedIds.size} of ${PrefsHelper.TOTAL_LESSONS} lessons completed
            📊 $percentage% course finished
        """.trimIndent()
    }

}