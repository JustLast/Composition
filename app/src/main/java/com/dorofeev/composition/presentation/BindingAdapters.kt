package com.dorofeev.composition.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.dorofeev.composition.R
import com.dorofeev.composition.domain.entity.GameResult

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.text_view_required_answers),
        count
    )
}

@BindingAdapter("score")
fun bindScore(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.text_view_score_answers),
        count
    )
}

@BindingAdapter("requiredAnswersPercent")
fun bindRequiredAnswersPercent(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.text_view_required_answers_percent),
        count
    )
}

@BindingAdapter("answersPercent")
fun bindAnswersPercent(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.text_view_answers_percent),
        calculatePercentOfRightAnswers(gameResult)
    )
}

private fun calculatePercentOfRightAnswers(gameResult: GameResult): Int {
    if (gameResult.countOfQuestions == 0) {
        return 0
    }
    return ((gameResult.countOfRightAnswers / gameResult.countOfQuestions.toDouble()) * 100).toInt()
}

@BindingAdapter("resultEmoji")
fun bindResultEmoji(imageView: ImageView, winner: Boolean) {
    imageView.setImageResource(getSmileImageResId(winner))
}

private fun getSmileImageResId(winner: Boolean): Int {
    return if (winner) {
        R.drawable.ic_emoji_smile_24
    } else {
        R.drawable.ic_emoji_cry_24
    }
}