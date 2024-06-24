package com.example.recall.bnt

import java.util.Date

/**
 * Data class for the Boston Naming Test (BNT)
 * @param noTests: Number of tests taken
 * @param totalCorrect: Total number of correct answers
 * @param totalErrors: Total number of errors
 * @param phoneticErrors: Number of phonetic errors
 * @param semanticErrors: Number of semantic errors
 * @param perseverativeErrors: Number of perseverative errors
 * @param cueUtilization: Number of cue utilization errors
 * @param date: Date of the game
 */
data class DataBNT(
    val noTests: Int = 0,
    val totalCorrect: Int = 0,
    val totalErrors: Int = 0,
    val phoneticErrors: Int = 0,
    val semanticErrors: Int = 0,
    val perseverativeErrors: Int = 0,
    val cueUtilization: Int = 0,
    val date: Date = Date()
) {
    constructor() : this(0, 0, 0, 0, 0, 0, 0, Date())
}
