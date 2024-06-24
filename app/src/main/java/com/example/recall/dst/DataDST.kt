package com.example.recall.dst

import java.util.Date

/**
 * Data class for the Data Structure Test
 * @param noTests: Number of tests taken
 * @param spanTime: Average time taken to answer a question
 * @param totalCorrect: Total number of correct answers
 * @param date: Date of the game
 */
data class DataDST(
    val noTests: Int = 0,
    val spanTime: Float = 0f,
    val totalCorrect: Int = 0,
    val date: Date = Date()
) {
    constructor() : this(0, 0f, 0, Date())
}
