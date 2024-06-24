package com.example.recall.sst

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
 * Activity to display the score of the SST game
 */
class ScoreSST : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    /**
     * Function to create the activity
     * @param savedInstanceState: Bundle? - The saved instance state
     */
    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_sst)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val spanTime = intent.getLongExtra("SpanTime", 0).toFloat()
        val totalCorrect = intent.getIntExtra("totalCorrect", 0)
        val date = Date(intent.getLongExtra("date", 0))

        findViewById<TextView>(R.id.spantime).text = "SpanTime: $spanTime s"
        findViewById<TextView>(R.id.totalCorrect).text = "Total Correct: $totalCorrect"
        findViewById<TextView>(R.id.date).text = "Date: $date"

        saveGameData(DataSST(totalCorrect, spanTime, totalCorrect, Date()))

        findViewById<Button>(R.id.back).setOnClickListener {
            val intent = Intent(this, Home::class.java).apply {
                putExtra("fragmentToLoad", "StartSST")
            }
            startActivity(intent)
            finish()
        }
    }

    /**
     * Function to save the game data to the Firestore database
     * @param gameData: DataSST - The game data to be saved
     */
    private fun saveGameData(gameData: DataSST) {
        val uid = auth.currentUser?.uid
        if (uid != null) {
            db.collection("users").document(uid)
                .collection("games").document("SST")
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
