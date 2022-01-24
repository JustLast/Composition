package com.dorofeev.composition.data

import com.dorofeev.composition.domain.entity.GameSettings
import com.dorofeev.composition.domain.entity.Level
import com.dorofeev.composition.domain.entity.Question
import com.dorofeev.composition.domain.repository.GameRepository
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

object GameRepositoryImpl : GameRepository {

    // 2: if we will use "0" or "1" we can get "0+0" or "1+1" in questions.
    // These are not interesting variants
    private const val MIN_SUM_VALUE = 2
    private const val MIN_ANSWER_VALUE = 1


    override fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question {

        // In this method a second parameter will be used by default,
        // that`s mean hi`s will be used like "parameter - 1".
        // If we want to use a second parameter in his max value
        // we need to increase him by 1
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        // visibleNumber is a first number which will be shown on a screen
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        // options is a set of answer options
        val options = HashSet<Int>()
        // A right answer
        val rightAnswer = sum - visibleNumber

        options.add(rightAnswer)

        val from = max(rightAnswer - countOfOptions, MIN_ANSWER_VALUE)
        val to = min(maxSumValue, rightAnswer + countOfOptions)

        while (options.size < countOfOptions) {
            options.add(Random.nextInt(from, to))
        }

        return Question(sum, visibleNumber, options.toList())
    }

    override fun getGameSetting(level: Level): GameSettings {
        return when (level) {
            Level.TEST -> {
                GameSettings(10, 3, 50, 8)
            }
            Level.EASY -> {
                GameSettings(10, 10, 70, 60)
            }
            Level.NORMAL -> {
                GameSettings(20, 20, 80, 40)
            }
            Level.HARD -> {
                GameSettings(30, 30, 90, 40)
            }
        }
    }
}