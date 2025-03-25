import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectandroid.databinding.ItemLessonBinding
import com.example.finalprojectandroid.Datamodel.Lesson

class LessonsAdapter(
    private val lessons: List<Lesson>,
    private val completedLessons: List<Boolean>,
    private val onClick: (Lesson) -> Unit
) : RecyclerView.Adapter<LessonsAdapter.LessonViewHolder>() {

    inner class LessonViewHolder(val binding: ItemLessonBinding) :
        RecyclerView.ViewHolder(binding.root)

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
        holder.binding.apply {
            tvLessonNumber.text = "Lesson ${lesson.id}"
            tvLessonTitle.text = lesson.title
            tvLessonDuration.text = lesson.duration
            tvCompletedStatus.text = if (completedLessons[position]) "✓ Completed" else "Incomplete"
            root.setOnClickListener { onClick(lesson) }
        }
    }

    override fun getItemCount() = lessons.size
}