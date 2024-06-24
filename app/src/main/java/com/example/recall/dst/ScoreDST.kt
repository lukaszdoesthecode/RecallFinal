package com.example.recall.dst

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
 * Activity for the Data Structure Test
 */
class ScoreDST : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    /**
     * Called when the activity is starting
     */
    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_dst)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val spanTime = intent.getFloatExtra("spanTime", 0f)
        val totalCorrect = intent.getIntExtra("totalCorrect", 0)
        val date = Date(intent.getLongExtra("date", 0))

        findViewById<TextView>(R.id.spantime).text = "Cue Utilization: $spanTime s"
        findViewById<TextView>(R.id.totalCorrect).text = "Total Correct: $totalCorrect"
        findViewById<TextView>(R.id.date).text = "Date: $date"

        saveGameData(DataDST(totalCorrect, spanTime, totalCorrect, date))

        findViewById<Button>(R.id.back).setOnClickListener {
            val intent = Intent(this, Home::class.java).apply {
                putExtra("fragmentToLoad", "StartDST")
            }
            startActivity(intent)
            finish()
        }
    }

    /**
     * Save the game data to Firestore
     */
    private fun saveGameData(gameData: DataDST) {
        val uid = auth.currentUser?.uid
        if (uid != null) {
            db.collection("users").document(uid)
                .collection("games").document("DST")
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
