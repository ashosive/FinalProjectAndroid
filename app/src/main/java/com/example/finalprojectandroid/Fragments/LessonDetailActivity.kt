package com.example.finalprojectandroid.Fragments

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.finalprojectandroid.Datamodel.Lesson
import com.example.finalprojectandroid.databinding.ActivityLessonDetailBinding

class LessonDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLessonDetailBinding
    private lateinit var prefs: SharedPreferences
    private lateinit var currentLesson: Lesson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE)

        currentLesson = intent.getSerializableExtra("LESSON") as Lesson

        binding.apply {
            tvTitle.text = "Lesson ${currentLesson.id}: ${currentLesson.title}"
            tvDuration.text = "Duration: ${currentLesson.duration}"
            tvDescription.text = currentLesson.description

            btnWatch.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(currentLesson.videoUrl)))
            }

            val completedLessons = prefs.getStringSet("COMPLETED_LESSONS", mutableSetOf())?.toMutableSet() ?: mutableSetOf()
            if (completedLessons.contains(currentLesson.id.toString())) {
                btnComplete.text = "COMPLETED"
                btnComplete.isEnabled = false
            }

            btnComplete.setOnClickListener {
                completedLessons.add(currentLesson.id.toString())
                prefs.edit().putStringSet("COMPLETED_LESSONS", completedLessons).apply()
                btnComplete.text = "COMPLETED"
                btnComplete.isEnabled = false
            }
        }
    }
}