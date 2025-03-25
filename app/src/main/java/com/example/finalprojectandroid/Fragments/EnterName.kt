import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.finalprojectandroid.databinding.EnternamefragmentBinding
import com.example.finalprojectandroid.Fragments.WelcomeActivity

class EnterNameFragment : Fragment() {
    private var _binding: EnternamefragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = EnternamefragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextBtn.setOnClickListener {
            val name = binding.UserNameTextBox.text.toString().trim()

            if (name.isNotEmpty()) {
                // Save name to SharedPreferences
                requireActivity().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
                    .edit()
                    .putString("user_name", name)
                    .apply()

                // Start com.example.finalprojectandroid.Fragments.WelcomeActivity using Intent
                val intent = Intent(requireActivity(), WelcomeActivity::class.java)
                startActivity(intent)
                requireActivity().finish() // Optional: close current activity
            } else {
                binding.UserNameTextBox.error = "Please enter your name"
                Toast.makeText(requireContext(), "Name cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}