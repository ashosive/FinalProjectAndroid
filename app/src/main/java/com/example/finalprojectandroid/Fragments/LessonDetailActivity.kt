package com.example.finalprojectandroid.Fragments

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.finalprojectandroid.Datamodel.Lesson
import com.example.finalprojectandroid.databinding.ActivityLessonDetailBinding

class LessonDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLessonDetailBinding
    private lateinit var prefs: SharedPreferences
    private lateinit var currentLesson: Lesson

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE)

        currentLesson = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("LESSON", Lesson::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("LESSON") as? Lesson
        } ?: throw IllegalArgumentException("Lesson data missing!")

        binding.apply {
            Title.text = "Lesson ${currentLesson.id}: ${currentLesson.title}"
            Duration.text = "Duration: ${currentLesson.duration} minutes"
            Description.text = currentLesson.description

            btnWatch.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, currentLesson.videoUrl.toUri()))
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
