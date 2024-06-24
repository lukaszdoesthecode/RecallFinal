package com.example.recall.st

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.recall.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Activity to play the ST game
 */
class ST : AppCompatActivity() {

    private lateinit var textWord: TextView
    private lateinit var red: Button
    private lateinit var green: Button
    private lateinit var blue: Button
    private lateinit var yellow: Button
    private lateinit var black: Button
    private lateinit var purple: Button

    private val colors = listOf("Red", "Green", "Blue", "Yellow","Black","Purple")
    private val colorValues = listOf(R.color.red, R.color.green,R.color.blue,R.color.yellow,R.color.black,R.color.purple)
    private val practiceTrials = 5
    private val totalTrials = 40

    private var trialCount = 0
    private var correctAnswers = 0
    private var incorrectAnswers = 0
    private var startTime = 0L
    private val results = mutableListOf<Long>()

    /**
     * Function to create the activity
     * @param savedInstanceState: Bundle? - The saved instance state
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_st)

        textWord = findViewById(R.id.text_word)
        red = findViewById(R.id.red)
        green = findViewById(R.id.green)
        blue = findViewById(R.id.blue)
        yellow = findViewById(R.id.yellow)
        black = findViewById(R.id.black)
        purple = findViewById(R.id.purple)

        val buttons = listOf(red, green, yellow, black, blue, purple)

        buttons.forEach { button ->
            button.setOnClickListener {
                handleButtonClick(button)
            }
        }

        startPracticeTrials()
    }

    /**
     * Function to start the practice trials
     */
    private fun startPracticeTrials() {
        trialCount = 0
        startNextTrial()
    }

    /**
     * Function to start the next trial
     */
    private fun startNextTrial() {
        if (trialCount >= practiceTrials + totalTrials) {
            showResults()
            return
        }

        val word = colors.random()
        val colorResId = colorValues.random()
        val textColor = ContextCompat.getColor(this, colorResId)
        textWord.text = word
        textWord.setTextColor(textColor)

        if (trialCount >= practiceTrials) {
            startTime = SystemClock.elapsedRealtime()
        }

        trialCount++
    }

    /**
     * Function to handle the button click
     */
    private fun handleButtonClick(button: Button) {
        if (trialCount <= practiceTrials) {
            startNextTrial()
            return
        }

        val elapsedTime = SystemClock.elapsedRealtime() - startTime
        results.add(elapsedTime)

        val selectedColor = when (button.id) {
            R.id.red -> R.color.red
            R.id.green -> R.color.green
            R.id.blue -> R.color.blue
            R.id.yellow -> R.color.yellow
            R.id.black -> R.color.black
            R.id.purple -> R.color.purple
            else -> 0
        }

        val textColor = ContextCompat.getColor(this, selectedColor)

        if (textColor == textWord.currentTextColor) {
            correctAnswers++
        } else {
            incorrectAnswers++
        }

        startNextTrial()
    }

    /**
     * Function to show the results
     */
    private fun showResults() {
        val averageTime = if (results.isNotEmpty()) results.average() else 0.0
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDate)

        val intent = Intent(this, ScoreST::class.java).apply {
            putExtra("averageTime", averageTime)
            putExtra("correctAnswers", correctAnswers)
            putExtra("incorrectAnswers", incorrectAnswers)
            putExtra("date", formattedDate)

        }
        startActivity(intent)
    }
}
