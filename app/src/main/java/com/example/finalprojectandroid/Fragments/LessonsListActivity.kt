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
        Lesson(1, "Introduction", "10:30", "Basic concepts", "https://youtu.be/BBWyXo-3JGQ"),
        Lesson(2, "UI Basics", "15:45", "Layouts and Views", "https://youtu.be/fis26HvvDII"),
        Lesson(3, "RecyclerView", "22:10", "Lists in Android", "https://youtu.be/Mc0XT58A1Z4"),
        Lesson(4, "Data Storage", "18:20", "SharedPreferences", "https://youtu.be/8obgNNlj3Eo"),
        Lesson(5, "Final Project", "25:00", "Complete App", "https://youtu.be/NZaH4tjwMY4")
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