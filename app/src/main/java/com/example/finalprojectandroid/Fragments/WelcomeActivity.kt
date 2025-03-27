package com.example.finalprojectandroid.Fragments

import android.annotation.SuppressLint
import com.example.finalprojectandroid.PrefsHelper
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.finalprojectandroid.PrefsHelper.Companion.KEY_COMPLETED_LESSONS
import com.example.finalprojectandroid.databinding.ActivityWelcomeBinding
import androidx.core.content.edit

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
    
    @SuppressLint("SetTextI18n", "SuspiciousIndentation")
    private fun setupUI() {
        val userName = prefs.getString("USER_NAME", "User") ?: "User"
        binding.welcomeText.text = "Welcome back\n $userName🔥"

        updateProgressStats()
        val json: String? = prefs.getString(KEY_COMPLETED_LESSONS, null)
            Log.d("***", "JSON: $json")

        binding.btnContinue.setOnClickListener {
            startActivity(Intent(this, LessonsListActivity::class.java))
        }

        binding.btnReset.setOnClickListener {
            prefs.edit() { clear() }
            startActivity(Intent(this, EnterNameActivity::class.java))
            finish()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateProgressStats() {
        val completedIds = prefs.getStringSet("COMPLETED_LESSONS", mutableSetOf()) ?: mutableSetOf()
        val percentage = (completedIds.size * 100) / PrefsHelper.TOTAL_LESSONS

       binding.progressStats.text ="""
            Your Progress:
            ✅ ${completedIds.size} of ${PrefsHelper.TOTAL_LESSONS} lessons completed
            📊 $percentage% course finished
        """.trimIndent()
    }

}