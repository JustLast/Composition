package com.dorofeev.composition.domain.entity

data class Question(
    private val sum: Int,
    private val visibleNumber: Int,
    private val options: List<Int>
) {
}