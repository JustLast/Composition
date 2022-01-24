package com.dorofeev.composition.domain.usecases

import com.dorofeev.composition.domain.entity.GameSettings
import com.dorofeev.composition.domain.entity.Level
import com.dorofeev.composition.domain.repository.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {
    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSetting(level)
    }
}