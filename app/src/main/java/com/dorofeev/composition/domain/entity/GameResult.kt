package com.dorofeev.composition.domain.entity

import java.io.Serializable

data class GameResult(
    private val winner: Boolean,
    private val countOfRightAnswers: Int,
    private val countOfQuestions: Int,
    private val gameSettings: GameSettings
) : Serializable{
}