package com.example.recall.results

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.android.gms.tasks.Tasks
import com.example.recall.bnt.DataBNT
import com.example.recall.dst.DataDST
import com.example.recall.sst.DataSST
import com.example.recall.st.DataST

/**
 * Fetches the game data from the Firestore database.
 *
 * @param callback The callback function to be called after the data is fetched.
 */
fun fetchGameData(callback: (List<DataGames>) -> Unit) {
    val uid = FirebaseAuth.getInstance().currentUser?.uid
    if (uid == null) {
        callback(emptyList())
        return
    }

    val db = FirebaseFirestore.getInstance()
    val gameDataList = mutableListOf<DataGames>()

    val collections = listOf("ST", "SST", "DST", "BNT")

    val tasks = collections.map { collection ->
        db.collection("users").document(uid)
            .collection("games").document(collection)
            .collection("sessions")
            .get()
    }

    Tasks.whenAllComplete(tasks).addOnSuccessListener {
        tasks.forEach { task ->
            if (task.isSuccessful) {
                val documents = task.result
                documents?.forEach { document ->
                    when (document.reference.parent.parent?.id) {
                        "ST" -> {
                            val data = document.toObject(DataST::class.java)
                            gameDataList.add(DataGames(
                                gameType = "ST",
                                averageTime = data.averageTime,
                                correctAnswers = data.correctAnswers,
                                incorrectAnswers = data.incorrectAnswers,
                                date = data.date
                            ))
                        }
                        "SST" -> {
                            val data = document.toObject(DataSST::class.java)
                            gameDataList.add(DataGames(
                                gameType = "SST",
                                noTests = data.noTests,
                                spanTime = data.spanTime,
                                totalCorrect = data.totalCorrect,
                                date = data.date
                            ))
                        }
                        "DST" -> {
                            val data = document.toObject(DataDST::class.java)
                            gameDataList.add(DataGames(
                                gameType = "DST",
                                noTests = data.noTests,
                                spanTime = data.spanTime,
                                totalCorrect = data.totalCorrect,
                                date = data.date
                            ))
                        }
                        "BNT" -> {
                            val data = document.toObject(DataBNT::class.java)
                            gameDataList.add(DataGames(
                                gameType = "BNT",
                                noTests = data.noTests,
                                totalCorrect = data.totalCorrect,
                                totalErrors = data.totalErrors,
                                phoneticErrors = data.phoneticErrors,
                                semanticErrors = data.semanticErrors,
                                perseverativeErrors = data.perseverativeErrors,
                                cueUtilization = data.cueUtilization,
                                date = data.date
                            ))
                        }
                    }
                }
            }
        }
        callback(gameDataList)
    }.addOnFailureListener {
        // Handle the error
        callback(emptyList())
    }
}
