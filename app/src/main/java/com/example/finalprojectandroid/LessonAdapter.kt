package com.example.finalprojectandroid
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectandroid.databinding.ItemLessonBinding
import com.example.finalprojectandroid.Datamodel.Lesson

class LessonsAdapter(
    private val lessons: List<Lesson>,
    private var completedLessons: List<Boolean>,
    private val onClick: (Lesson) -> Unit
) : RecyclerView.Adapter<LessonsAdapter.LessonViewHolder>() {

    fun updateCompletedLessons(newCompletedLessons: List<Boolean>) {
        completedLessons = newCompletedLessons
        notifyDataSetChanged()
    }

    inner class LessonViewHolder(val binding: ItemLessonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(lesson: Lesson, isCompleted: Boolean) {
            binding.apply {
                lessonNumber.text = "Lesson ${lesson.id}"
                lessonTitle.text = lesson.title
                duration.text = lesson.duration
                lessonStatus.text = if (isCompleted) "✓ Completed" else "Incomplete"
                root.setOnClickListener { onClick(lesson) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val binding = ItemLessonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LessonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val lesson = lessons[position]
        val isCompleted = completedLessons[position]
        holder.bind(lesson, isCompleted)
    }

    override fun getItemCount() = lessons.size
}