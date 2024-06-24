package com.example.recall.st

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.recall.R
import com.example.recall.main.Home
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date

/**
 * Activity to display the score of the ST game
 */
class ScoreST : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    /**
     * Function to create the activity
     * @param savedInstanceState: Bundle? - The saved instance state
     */
    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_st)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val averageTime = intent.getDoubleExtra("averageTime", 0.0).toFloat()
        val correctAnswers = intent.getIntExtra("correctAnswers", 0)
        val incorrectAnswers = intent.getIntExtra("incorrectAnswers", 0)
        val date = Date(intent.getLongExtra("date", 0))

        findViewById<TextView>(R.id.averageTime).text = "SpanTime: $averageTime s"
        findViewById<TextView>(R.id.correctAnswers).text = "Total Correct: $correctAnswers"
        findViewById<TextView>(R.id.incorrectAnswers).text = "Total Incorrect: $incorrectAnswers"
        findViewById<TextView>(R.id.date).text = "Date: $date"

        saveGameData(DataST(averageTime, correctAnswers, incorrectAnswers, Date()))

        findViewById<Button>(R.id.back).setOnClickListener {
            val intent = Intent(this, Home::class.java).apply {
                putExtra("fragmentToLoad", "StartST")
            }
            startActivity(intent)
            finish()
        }
    }

    /**
     * Function to save the game data to the Firestore database
     * @param gameData: DataST - The game data to be saved
     */
    private fun saveGameData(gameData: DataST) {
        val uid = auth.currentUser?.uid
        if (uid != null) {
            db.collection("users").document(uid)
                .collection("games").document("ST")
                .collection("sessions").add(gameData)
                .addOnSuccessListener {
                    // Successfully saved game data
                }
                .addOnFailureListener { _ ->
                    // Handle the failure
                }
        }
    }
}
