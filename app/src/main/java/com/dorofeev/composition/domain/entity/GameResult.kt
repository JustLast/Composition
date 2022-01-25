package com.dorofeev.composition.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResult(
    private val winner: Boolean,
    private val countOfRightAnswers: Int,
    private val countOfQuestions: Int,
    private val gameSettings: GameSettings
) : Parcelable