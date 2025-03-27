package com.example.finalprojectandroid.Datamodel
import java.io.Serializable

data class Lesson(
    val id: Int,
    val title: String,
    val duration: String,
    val description: String,
    val videoUrl: String
) : Serializable
