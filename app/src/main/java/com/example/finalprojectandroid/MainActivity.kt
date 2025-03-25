package com.example.finalprojectandroid

import EnterNameFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.finalprojectandroid.Fragments.WelcomeActivity
import com.example.finalprojectandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var prefsHelper: PrefsHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefsHelper = PrefsHelper(this)

        if (savedInstanceState == null) {
            val fragment = if (prefsHelper.isFirstTime()) {
                EnterNameFragment()
            } else {
                WelcomeActivity()
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment as Fragment)
                .commit()
        }
    }
}