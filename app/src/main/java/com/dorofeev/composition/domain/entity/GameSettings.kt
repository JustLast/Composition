package com.dorofeev.composition.domain.entity

data class GameSettings(
    private val maxSumValue: Int,
    private val minCountOfRightAnswers: Int,
    private val minPercentOfRightAnswers: Int,
    private val gameTimeInSeconds: Int
) {
}