package com.example.technicalsumm2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.technicalsumm2.databinding.FragmentAboutUsBinding

class AboutUsFragment : Fragment() {

    private var _binding: FragmentAboutUsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAboutUsBinding.inflate(inflater, container, false)

        val members = listOf(
            Member(
                R.drawable.alex,
                "Alex Nicolas",
                "Project Leader",
                "Passionate Android developer and group leader. Responsible for coordinating the team, implementing core application features, and ensuring the project is completed successfully."

            ),
            Member(
                R.drawable.angelo,
                "Angelo Kyle B. Golfo",
                "Lead Programmer",
                "Serves as the team's Lead Programmer, responsible for developing core application features, writing efficient code, fixing bugs, and ensuring the app performs reliably while collaborating with the team throughout development."
            ),
            Member(
                R.drawable.gian,
                "Gian Emmanuel Bendiola",
                "Designer",
                "Serves as the team's UI/UX Designer, responsible for designing user-friendly interfaces, creating visually appealing layouts, and ensuring a smooth and engaging user experience throughout the application."
            )
        )

        binding.viewPager.adapter = MemberAdapter(members)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}