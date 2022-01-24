package com.dorofeev.composition.domain.repository

import com.dorofeev.composition.domain.entity.GameSettings
import com.dorofeev.composition.domain.entity.Level
import com.dorofeev.composition.domain.entity.Question

interface GameRepository {
    fun generateQuestion(maxSumValue: Int, countOfOptions: Int) : Question
    fun getGameSetting(level: Level) : GameSettings
}