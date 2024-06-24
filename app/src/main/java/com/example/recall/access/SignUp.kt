package com.example.recall.access

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.recall.R
import com.example.recall.main.Snackbar

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

/**
 * SignUp class is responsible for the user sign up.
 */
class SignUp : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    private var email: EditText? = null
    private var name: EditText? = null
    private var surname: EditText? = null
    private var age: String? = null
    private var nationality: String? = null
    private var education: String? = null
    private var password: EditText? = null
    private var repeat: EditText? = null

    /**
     * onCreate function is called when the activity is starting.
     * It sets the content view and initializes the Firebase authentication and Firestore.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        age = intent.getStringExtra("AGE")
        nationality = intent.getStringExtra("NATIONALITY")
        education = intent.getStringExtra("EDUCATION")

        email = findViewById(R.id.emailEV)
        name = findViewById(R.id.nameEV)
        surname = findViewById(R.id.surnameEV)
        password = findViewById(R.id.passwordEV)
        repeat = findViewById(R.id.repeatEV)

        val register: Button = findViewById(R.id.save)
        register.setOnClickListener {
            if (validate()) {
                registerUser()
            }
        }

        val logTv = findViewById<TextView>(R.id.LogTV)
        logTv.setOnClickListener {
            goToLogin()
        }
    }

    /**
     * validate function checks if the email, name, surname, and password fields are empty.
     */
    private fun validate(): Boolean {
        if (name?.text.isNullOrBlank()) {
            Snackbar.showSnackbar(findViewById(android.R.id.content), "Username is required")
            return false
        }
        if (surname?.text.isNullOrBlank()) {
            Snackbar.showSnackbar(findViewById(android.R.id.content), "Surname is required")
            return false
        }
        if (email?.text.isNullOrBlank()) {
            Snackbar.showSnackbar(findViewById(android.R.id.content), "Email is required")
            return false
        }
        if (!email?.text.toString().contains("@")) {
            Snackbar.showSnackbar(findViewById(android.R.id.content), "Provide valid email")
            return false
        }
        if (password?.text.isNullOrBlank()) {
            Snackbar.showSnackbar(findViewById(android.R.id.content), "Password is required")
            return false
        }
        if (password!!.length() < 6) {
            Snackbar.showSnackbar(findViewById(android.R.id.content), "Password too short")
            return false
        }

        var capital = false
        var number = false
        val numbers = setOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')

        for (char in password?.text.toString()) {
            if (numbers.contains(char)) {
                number = true
                break
            }
        }
        for (char in password?.text.toString()) {
            if (char.isUpperCase()) {
                capital = true
                break
            }
        }

        if (!capital) {
            Snackbar.showSnackbar(
                findViewById(android.R.id.content),
                "Password needs a Capital Letter"
            )
            return false
        } else if (!number) {
            Snackbar.showSnackbar(findViewById(android.R.id.content), "Password needs a Number")
            return false
        }

        if (password?.text?.toString() != repeat?.text?.toString()) {
            Snackbar.showSnackbar(
                findViewById(android.R.id.content),
                "The passwords aren't the same!"
            )
            return false
        }
        Snackbar.showSnackbar(findViewById(android.R.id.content), "Success")
        return true
    }

    /**
     * registerUser function registers the user using the email and password.
     */
    private fun registerUser() {
        val emailStr = email?.text.toString()
        val passwordStr = password?.text.toString()

        auth.createUserWithEmailAndPassword(emailStr, passwordStr)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    saveUserData(user?.uid)
                } else {
                    Log.e("SignUp", "Authentication failed: ${task.exception?.message}")
                    Snackbar.showSnackbar(findViewById(android.R.id.content), "Authentication failed.")
                }
            }
    }

    /**
     * saveUserData function saves the user data to Firestore.
     */
    private fun saveUserData(uid: String?) {
        if (uid == null) {
            Log.e("SignUp", "User ID is null, cannot save user data")
            return
        }

        val user = hashMapOf(
            "name" to name?.text.toString(),
            "surname" to surname?.text.toString(),
            "email" to email?.text.toString(),
            "age" to age,
            "nationality" to nationality,
            "education" to education
        )

          db.collection("users").document(uid)
            .set(user)
            .addOnSuccessListener {
                Log.d("SignUp", "User data successfully saved")
                goToLogin()
            }
            .addOnFailureListener { e ->
                Log.e("SignUp", "Failed to save user data: ${e.message}")
                Snackbar.showSnackbar(findViewById(android.R.id.content), "Failed to save user data.")
            }
    }

    /**
     * goToLogin function navigates to the LogIn activity.
     */
    private fun goToLogin() {
        val intent = Intent(this, LogIn::class.java)
        startActivity(intent)
        finish()
    }
}
