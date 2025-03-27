package com.example.finalprojectandroid

import com.example.finalprojectandroid.Fragments.EnterNameActivity
import com.example.finalprojectandroid.Fragments.WelcomeActivity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE)

        val userName = prefs.getString("USER_NAME", null)
        val intent = if (userName.isNullOrEmpty()) {
            Intent(this, EnterNameActivity::class.java)
        } else {
            Intent(this, WelcomeActivity::class.java)
        }

        startActivity(intent)
        finish()

}
}