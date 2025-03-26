package com.example.finalprojectandroid.Fragments

import PrefsHelper
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.finalprojectandroid.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    private lateinit var prefsHelper: PrefsHelper
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefsHelper = PrefsHelper(this)

        prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE)

        // Check if we should redirect to EnterName
        if (shouldShowEnterName()) {
            redirectToEnterName()
            return
        }

        // Otherwise show welcome screen
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
    }

    private fun shouldShowEnterName(): Boolean {
        return prefsHelper.isFirstTime() || prefsHelper.getUserName().isNullOrEmpty()
    }

    private fun redirectToEnterName() {
        startActivity(Intent(this, EnterNameActivity::class.java))
        finish()
    }

    private fun setupUI() {
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

    override fun onResume() {
        super.onResume()
        if (!shouldShowEnterName()) {
            updateProgressStats()
        }
    }

    private fun updateProgressStats() {
        val completed = prefsHelper.getCompletedCount()
        val total = PrefsHelper.TOTAL_LESSONS
        val percentage = prefsHelper.getCompletionPercentage()

        binding.progressStats.text = """
            Your Progress:
            ✅ $completed of $total lessons completed
            📊 $percentage% course finished
        """.trimIndent()

        // For debugging - verify data in Logcat
        Log.d("ProgressDebug", "Completed lessons: ${prefsHelper.getCompletedLessons()}")
    }
}