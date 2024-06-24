package com.example.recall.bnt

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
 * ScoreBNT class is responsible for displaying the Boston Naming Test (BNT) score.
 */
class ScoreBNT : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    /**
     * onCreate function is called when the activity is starting.
     * It sets the content view and initializes the BNT score fields.
     */
    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_bnt)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val totalCorrect = intent.getIntExtra("totalCorrect", 0)
        val totalErrors = intent.getIntExtra("totalErrors", 0)
        val phoneticErrors = intent.getIntExtra("phoneticErrors", 0)
        val semanticErrors = intent.getIntExtra("semanticErrors", 0)
        val perseverativeErrors = intent.getIntExtra("perseverativeErrors", 0)
        val cueUtilization = intent.getIntExtra("cueUtilization", 0)
        val date = intent.getStringExtra("date")

        findViewById<TextView>(R.id.totalCorrect).text = "Total Correct: $totalCorrect"
        findViewById<TextView>(R.id.totalErrors).text = "Total Errors: $totalErrors"
        findViewById<TextView>(R.id.phoneticErrors).text = "Phonetic Errors: $phoneticErrors"
        findViewById<TextView>(R.id.semanticErrors).text = "Semantic Errors: $semanticErrors"
        findViewById<TextView>(R.id.perseverativeErrors).text = "Perseverative Errors: $perseverativeErrors"
        findViewById<TextView>(R.id.cueUtilization).text = "Cue Utilization: $cueUtilization"
        findViewById<TextView>(R.id.date).text = "Date: $date"

        saveGameData(DataBNT(1, totalCorrect, totalErrors, phoneticErrors, semanticErrors, perseverativeErrors, cueUtilization, Date()))

        findViewById<Button>(R.id.back).setOnClickListener {
            val intent = Intent(this, Home::class.java).apply {
                putExtra("fragmentToLoad", "StartBNT")
            }
            startActivity(intent)
            finish()
        }
    }

    /**
     * saveGameData function saves the BNT game data to Firestore.
     */
    private fun saveGameData(gameData: DataBNT) {
        val uid = auth.currentUser?.uid
        if (uid != null) {
            db.collection("users").document(uid)
                .collection("games").document("BNT")
                .collection("sessions").add(gameData)
                .addOnSuccessListener {
                    // Successfully saved game data
                }
                .addOnFailureListener { e ->
                    // Handle the failure
                }
        }
    }
}
