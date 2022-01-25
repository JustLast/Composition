package com.dorofeev.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.dorofeev.composition.R
import com.dorofeev.composition.databinding.FragmentGameFinishedBinding
import com.dorofeev.composition.domain.entity.GameResult
import java.lang.RuntimeException

class GameFinishedFragment : Fragment() {

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    private lateinit var gameResult: GameResult

    companion object {

        private const val KEY_GAME_RESULT = "game_result"

        fun newInstance(gameResult: GameResult): GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_RESULT, gameResult)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListener()
        bindViews()
    }

    private fun setupClickListener() {
        // Set a listener for "Back" button of activity
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                retryGame()
            }
        })

        binding.buttonTryAgain.setOnClickListener {
            retryGame()
        }
    }

    private fun parseArgs() {
        requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT)?.let {
            gameResult = it
        }
    }

    private fun bindViews() {
        binding.ivEmojiResult.setImageResource(getSmileImageResId())

        binding.tvRequiredAnswers.text = String.format(
            getString(R.string.text_view_required_answers),
            gameResult.gameSettings.minCountOfRightAnswers
        )

        binding.tvScoreAnswers.text = String.format(
            getString(R.string.text_view_score_answers),
            gameResult.countOfRightAnswers
        )

        binding.tvRequiredAnswersPercent.text = String.format(
            getString(R.string.text_view_required_answers_percent),
            gameResult.gameSettings.minPercentOfRightAnswers
        )

        binding.tvAnswersPercent.text = String.format(
            getString(R.string.text_view_answers_percent),
            calculatePercentOfRightAnswers()
        )
    }

    private fun getSmileImageResId(): Int {
        return if (gameResult.winner) {
            R.drawable.ic_emoji_smile_24
        } else {
            R.drawable.ic_emoji_cry_24
        }
    }

    private fun calculatePercentOfRightAnswers(): Int {
        if (gameResult.countOfQuestions == 0) {
            return 0
        }
        return ((gameResult.countOfRightAnswers / gameResult.countOfQuestions.toDouble()) * 100).toInt()
    }

    private fun retryGame() {
        requireActivity().supportFragmentManager.popBackStack(GameFragment.NAME, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}