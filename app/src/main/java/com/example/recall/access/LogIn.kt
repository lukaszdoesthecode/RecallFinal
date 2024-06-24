package com.example.recall.access

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.recall.main.Home
import com.example.recall.R
import com.example.recall.main.Snackbar
import com.example.recall.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth

/**
 * LogIn class is responsible for the user login.
 */
class LogIn : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding
    private lateinit var auth: FirebaseAuth
    private var email: EditText? = null
    private var password: EditText? = null

    /**
     * onCreate function is called when the activity is starting.
     * It sets the content view and initializes the Firebase authentication.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val signUp: TextView = findViewById(R.id.SignUpTV)
        email = binding.emailEV
        password = binding.passwordEV

        signUp.setOnClickListener { goToQuestionnaire() }

        binding.save.setOnClickListener {
            if (validate()) {
                loginUser()
            }
        }
    }

    /**
     * validate function checks if the email and password fields are empty.
     */
    private fun validate(): Boolean {
        if (email?.text.toString().isEmpty() || password?.text.toString().isEmpty()) {
            Snackbar.showSnackbar(binding.root, "Fill Out Your Information")
            return false
        }
        return true
    }

    /**
     * loginUser function logs in the user using the email and password.
     */
    private fun loginUser() {
        val emailStr = email?.text.toString()
        val passwordStr = password?.text.toString()

        auth.signInWithEmailAndPassword(emailStr, passwordStr)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    goHome()
                } else {
                    Snackbar.showSnackbar(binding.root, "Authentication failed.")
                }
            }
    }

    /**
     * goToQuestionnaire function navigates to the Questionnaire activity.
     */
    private fun goToQuestionnaire() {
        val intent = Intent(this, Questionnaire::class.java)
        startActivity(intent)
        finish()
    }

    /**
     * goHome function navigates to the Home activity.
     */
    private fun goHome() {
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
        finish()
    }
}
