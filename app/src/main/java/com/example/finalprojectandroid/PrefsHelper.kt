package com.example.finalprojectandroid

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.core.content.edit

class PrefsHelper(context: Context) {
    private val sharedPref: SharedPreferences =
        context.getSharedPreferences("EducationAppPrefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    companion object {
        const val KEY_FIRST_TIME = "first_time"
        const val KEY_USER_NAME = "user_name"
        const val KEY_COMPLETED_LESSONS = "completed_lessons"
        const val TOTAL_LESSONS = 5
    }
    private fun getCompletedLessons(): List<Boolean> {
        val json = sharedPref.getString(KEY_COMPLETED_LESSONS, null)
        return json?.let {
            try {
                gson.fromJson(it, object : TypeToken<List<Boolean>>() {}.type)
            } catch (e: Exception) {
                List(TOTAL_LESSONS) { false }
            }
        } ?: List(TOTAL_LESSONS) { false }
    }

    private fun setCompletedLessons(completed: List<Boolean>) {
        if (completed.size == TOTAL_LESSONS) {
            sharedPref.edit() { putString(KEY_COMPLETED_LESSONS, gson.toJson(completed)) }
        }
    }

    fun getCompletedCount(): Int = getCompletedLessons().count { it }


    fun getPendingCount(): Int = TOTAL_LESSONS - getCompletedCount()
    fun getCompletionPercentage(): Int = (getCompletedCount() * 100 / TOTAL_LESSONS)

    fun markLessonComplete(lessonId: Int) {
        if (lessonId in 1..TOTAL_LESSONS) {
            val completed = getCompletedLessons().toMutableList()
            completed[lessonId - 1] = true
            setCompletedLessons(completed)
        }
    }

    fun clearUserData() {
        sharedPref.edit().apply {
            remove(KEY_USER_NAME)
            remove(KEY_COMPLETED_LESSONS)
            putBoolean(KEY_FIRST_TIME, true)
            apply()
        }
    }
}