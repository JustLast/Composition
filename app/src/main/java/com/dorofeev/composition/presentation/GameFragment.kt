package com.dorofeev.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dorofeev.composition.R
import com.dorofeev.composition.databinding.FragmentGameBinding
import java.lang.RuntimeException

class GameFragment : Fragment() {

    private var _bindind: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _bindind ?: throw RuntimeException("FragmentGameBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bindind = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bindind = null
    }
}