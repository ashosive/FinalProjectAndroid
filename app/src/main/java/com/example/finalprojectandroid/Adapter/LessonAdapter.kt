package com.example.finalprojectandroid.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectandroid.Datamodel.Lesson
import com.example.finalprojectandroid.databinding.ItemLessonBinding

class LessonAdapter(
    private val lessons: List<Lesson>,
    private val completedLessons: Set<String>,
    private val onClick: (Lesson) -> Unit
) : RecyclerView.Adapter<LessonAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemLessonBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLessonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lesson = lessons[position]
        holder.binding.apply {
            lessonNumber.text = "Lesson ${lesson.id}"
            lessonTitle.text = lesson.title
            duration.text = lesson.duration
            lessonStatus.text = if (completedLessons.contains(lesson.id.toString())) "✓ Completed" else "Pending"
            root.setOnClickListener { onClick(lesson) }
        }
    }
    override fun getItemCount() = lessons.size
}