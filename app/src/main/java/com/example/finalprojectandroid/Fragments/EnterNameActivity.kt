package com.example.finalprojectandroid.Fragments
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.finalprojectandroid.databinding.ActivityEnterNameBinding
import androidx.core.content.edit

class EnterNameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEnterNameBinding
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnterNameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE)

        binding.btnContinue.setOnClickListener {
            val name = binding.Screen1UserName.text.toString().trim()
            if (name.isNotEmpty()) {
                prefs.edit() { putString("USER_NAME", name) }
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()
            } else {
                binding.Screen1UserName.error = "Please enter your name"
            }
        }
    }
}