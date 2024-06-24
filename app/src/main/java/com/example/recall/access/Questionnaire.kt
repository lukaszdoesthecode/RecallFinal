package com.example.recall.access

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.recall.R
import com.example.recall.main.Snackbar

/**
 * Questionnaire class is responsible for the user questionnaire.
 */
class Questionnaire : AppCompatActivity() {

    private lateinit var countries: Array<String>
    private lateinit var nationalitySpinner: Spinner

    /**
     * onCreate function is called when the activity is starting.
     * It sets the content view and initializes the questionnaire fields.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionnaire)

        val ageEditText: EditText = findViewById(R.id.ageEV)

        nationalitySpinner = findViewById(R.id.nationality_spinner)
        countries = resources.getStringArray(R.array.countries_array)

        val educationSpinner: Spinner = findViewById(R.id.education_spinner)
        val educationLevels = resources.getStringArray(R.array.education_levels_array)
        val educationAdapter = ArrayAdapter(this, R.layout.spinner_item, educationLevels)
        educationAdapter.setDropDownViewResource(R.layout.spinner_item)
        educationSpinner.adapter = educationAdapter

        val nationalityAdapter = ArrayAdapter(this, R.layout.spinner_item, countries)
        nationalityAdapter.setDropDownViewResource(R.layout.spinner_item)
        nationalitySpinner.adapter = nationalityAdapter

        val saveButton: Button = findViewById(R.id.save)
        saveButton.setOnClickListener {
            val age = ageEditText.text.toString()
            val nationality = nationalitySpinner.selectedItem.toString()
            val education = educationSpinner.selectedItem.toString()

            if (age.isEmpty() || nationality.isEmpty() || education.isEmpty()) {
                Snackbar.showSnackbar(
                    findViewById(android.R.id.content),
                    "Please fill out all fields"
                )
            } else {
                val intent = Intent(this, SignUp::class.java).apply {
                    putExtra("AGE", age)
                    putExtra("NATIONALITY", nationality)
                    putExtra("EDUCATION", education)
                }
                startActivity(intent)
            }
        }
    }
}
