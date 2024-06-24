package com.example.recall.sst

import java.util.Date

/**
 * Data class for storing the data of the SST game
 * @param noTests: Number of tests taken
 * @param spanTime: Average time taken to answer a question
 * @param totalCorrect: Total number of correct answers
 * @param date: Date of the game
 */
data class DataSST(
    val noTests: Int = 0,
    val spanTime: Float = 0f,
    val totalCorrect: Int = 0,
    val date: Date = Date() 
) {
    constructor() : this(0, 0f, 0, Date())
}
