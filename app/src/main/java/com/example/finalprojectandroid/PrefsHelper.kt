package com.example.finalprojectandroid
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PrefsHelper(context: Context) {
    private val sharedPref: SharedPreferences =
        context.getSharedPreferences("EducationAppPrefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    companion object {
        const val KEY_FIRST_TIME = "first_time"
        const val KEY_USER_NAME = "user_name"
        const val KEY_COMPLETED_LESSONS = "completed_lessons"
    }

    fun isFirstTime(): Boolean = sharedPref.getBoolean(KEY_FIRST_TIME, true)
    fun setFirstTime(value: Boolean) = sharedPref.edit().putBoolean(KEY_FIRST_TIME, value).apply()
    fun getUserName(): String? = sharedPref.getString(KEY_USER_NAME, null)
    fun setUserName(name: String) = sharedPref.edit().putString(KEY_USER_NAME, name).apply()

    fun getCompletedLessons(): List<Boolean> {
        val json = sharedPref.getString(KEY_COMPLETED_LESSONS, null)
        return json?.let {
            gson.fromJson(it, object : TypeToken<List<Boolean>>() {}.type)
        } ?: List(5) { false }
    }

    fun setCompletedLessons(completed: List<Boolean>) {
        sharedPref.edit().putString(KEY_COMPLETED_LESSONS, gson.toJson(completed)).apply()
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