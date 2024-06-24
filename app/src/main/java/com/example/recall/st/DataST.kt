package com.example.recall.st

import java.util.Date

/**
 * Data class for storing the data of the ST game
 * @param averageTime: Average time taken to answer a question
 * @param correctAnswers: Total number of correct answers
 * @param incorrectAnswers: Total number of incorrect answers
 * @param date: Date of the game
 */
data class DataST(
    val averageTime: Float = 0f,
    val correctAnswers: Int = 0,
    val incorrectAnswers: Int = 0,
    val date: Date = Date()
) {
    constructor() : this(0f, 0, 0, Date())
}
