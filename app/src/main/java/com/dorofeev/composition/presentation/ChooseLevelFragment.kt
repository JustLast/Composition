package com.dorofeev.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dorofeev.composition.R
import com.dorofeev.composition.databinding.FragmentChooseLevelBinding
import com.dorofeev.composition.domain.entity.Level
import java.lang.RuntimeException

class ChooseLevelFragment : Fragment() {

    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickListeners()
    }

    private fun launchGameFragment(level: Level) {
        findNavController().navigate(
            ChooseLevelFragmentDirections.actionChooseLevelFragmentToGameFragment(level)
        )
    }

    private fun setClickListeners() {
        binding.buttonLevelTest.setOnClickListener {
            launchGameFragment(Level.TEST)
        }
        binding.buttonLevelEasy.setOnClickListener {
            launchGameFragment(Level.EASY)
        }
        binding.buttonLevelNormal.setOnClickListener {
            launchGameFragment(Level.NORMAL)
        }
        binding.buttonLevelHard.setOnClickListener {
            launchGameFragment(Level.HARD)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}