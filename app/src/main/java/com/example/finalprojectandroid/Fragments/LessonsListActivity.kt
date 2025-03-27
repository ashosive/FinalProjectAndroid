package com.example.finalprojectandroid.Fragments

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalprojectandroid.Datamodel.Lesson
import com.example.finalprojectandroid.LessonsAdapter
import com.example.finalprojectandroid.databinding.ActivityLessonsListBinding

class LessonsListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLessonsListBinding
    private lateinit var prefs: SharedPreferences
    private lateinit var adapter: LessonsAdapter
    private val lessons = listOf(
        Lesson(1, "Bitcoin Whitepaper", "15:30",
            "Analyze Satoshi's original paper covering decentralization, proof-of-work, and the double-spending solution through blockchain technology.",
            "https://youtu.be/41JCpzvnn_0"),

        Lesson(2, "Wallet Security", "22:15",
            "Learn public/private key cryptography, HD wallets, and cold storage methods to securely store cryptocurrencies.",
            "https://youtu.be/AvPdaNb35qY"),

        Lesson(3, "Mining Explained", "19:45",
            "Understand how mining works including hash rate, difficulty adjustments, and the energy-intensive proof-of-work consensus mechanism.",
            "https://youtu.be/t4p4iMqmxbQ"),

        Lesson(4, "Altcoins & Tokens", "17:20",
            "Explore major altcoins (Ethereum, Litecoin), stablecoins, and token standards like ERC-20 that expand blockchain functionality.",
            "https://youtu.be/1PU72AXQfXY"),

        Lesson(5, "Crypto Trading Basics", "25:50",
            "Master exchange fundamentals, candlestick patterns, and risk management strategies for cryptocurrency trading.",
            "https://youtu.be/GmOzih6I1zs")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE)

        val completedLessons = getCompletedLessonsFromPrefs()

        adapter = LessonsAdapter(lessons, completedLessons) { lesson ->
            val intent = Intent(this, LessonDetailActivity::class.java).apply {
                putExtra("LESSON", lesson)
            }
            startActivity(intent)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@LessonsListActivity)
            adapter = this@LessonsListActivity.adapter
        }
    }

    override fun onResume() {
        super.onResume()
        val updatedCompletedLessons = getCompletedLessonsFromPrefs()
        adapter.updateCompletedLessons(updatedCompletedLessons)
    }

    private fun getCompletedLessonsFromPrefs(): List<Boolean> {
        val completedIds = prefs.getStringSet("COMPLETED_LESSONS", mutableSetOf()) ?: mutableSetOf()
        return lessons.map { lesson ->
            completedIds.contains(lesson.id.toString())
        }
    }
}