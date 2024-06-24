package com.example.recall.results

import java.util.Date

/**
 * Data class for the games played by the user.
 *
 * @param gameType The type of game played.
 * @param noTests The number of tests taken.
 * @param spanTime The time taken to complete the game.
 * @param totalCorrect The total number of correct answers.
 * @param totalErrors The total number of errors.
 * @param phoneticErrors The number of phonetic errors.
 * @param semanticErrors The number of semantic errors.
 * @param perseverativeErrors The number of perseverative errors.
 * @param cueUtilization The cue utilization.
 * @param averageTime The average time taken to answer a question.
 * @param correctAnswers The number of correct answers.
 * @param incorrectAnswers The number of incorrect answers.
 * @param date The date the game was played.
 */
data class DataGames(
    val gameType: String,
    val noTests: Int? = null,
    val spanTime: Float? = null,
    val totalCorrect: Int? = null,
    val totalErrors: Int? = null,
    val phoneticErrors: Int? = null,
    val semanticErrors: Int? = null,
    val perseverativeErrors: Int? = null,
    val cueUtilization: Int? = null,
    val averageTime: Float? = null,
    val correctAnswers: Int? = null,
    val incorrectAnswers: Int? = null,
    val date: Date
)
