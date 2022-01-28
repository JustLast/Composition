package com.dorofeev.composition.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.dorofeev.composition.R
import com.dorofeev.composition.domain.entity.GameResult


// Binding Adapters for the Game Fragment
@BindingAdapter("enoughCount")
fun bindEnoughCount(textView: TextView, enough: Boolean) {
    textView.setTextColor(getColorByState(textView.context, enough))
}

@BindingAdapter("enoughPercent")
fun bindEnoughPercent(progressBar: ProgressBar, enough: Boolean) {
    val color = getColorByState(progressBar.context, enough)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

private fun getColorByState(context: Context, state: Boolean) : Int {
    val colorResId = if (state) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorResId)
}

@BindingAdapter("numberAsText")
fun bindNumberAsText(textView: TextView, number: Int) {
    textView.text = number.toString()
}


// Binding Adapters for the Game Finished Fragment
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