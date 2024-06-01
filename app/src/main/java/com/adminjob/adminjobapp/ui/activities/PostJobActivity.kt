package com.adminjob.adminjobapp.ui.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adminjob.adminjobapp.databinding.ActivityPostJobBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.collections.hashMapOf as hashMapOf1

class PostJobActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostJobBinding
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostJobBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            saveJobToFirestore()
        }
    }

    private fun saveJobToFirestore() {
        val jobCompany = binding.etFirstName.text.toString().trim()
        val jobPosition = binding.etLastName.text.toString().trim()
        val jobSalary = binding.etEmail.text.toString().trim()
        val jobLastDate = binding.etContact.text.toString().trim()
        val jobExperience = binding.etPassword.text.toString().trim()
        val jobVacancy = binding.etSummary.text.toString().trim()
        val jobId = binding.etPosition.text.toString().trim()
        val jobQualification = binding.etVacancy.text.toString().trim()
        val skill = binding.etPlatform.text.toString().trim()
        val jobDescription = binding.etMinExp.text.toString().trim()
        val jobPostBy = binding.etMaxExp.text.toString().trim()
        val email = binding.etMaxExp11.text.toString().trim()

        // Check if any field is empty
        if (jobCompany.isEmpty() || jobPosition.isEmpty() || jobSalary.isEmpty() || jobLastDate.isEmpty() ||
            jobExperience.isEmpty() || jobVacancy.isEmpty() || jobId.isEmpty() || jobQualification.isEmpty() ||
            skill.isEmpty() || jobDescription.isEmpty() || jobPostBy.isEmpty() || email.isEmpty()
        ) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Create a HashMap to store all the job details
        val job = hashMapOf1(
            "Company" to jobCompany,
            "Position" to jobPosition,
            "Salary" to jobSalary,
            "LastDate" to jobLastDate,
            "Experience" to jobExperience,
            "Vacancy" to jobVacancy,
            "Job Id" to jobId,
            "Qualification" to jobQualification,
            "Skills" to skill,
            "Job description" to jobDescription,
            "Job Posted By" to jobPostBy,
            "Email" to email
        )

        // Get the current user ID
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val userId = currentUser.uid

            // Add the job under the user's document in the "users" collection
            db.collection("Jobinformation")
                .add(job)
                .addOnSuccessListener {
                    showSuccessDialog()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error adding job: ${e.message}", Toast.LENGTH_SHORT)
                        .show()
                }
        } else {
            Toast.makeText(this, "User not authenticated", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showSuccessDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("Job posted successfully")
            .setCancelable(false)
            .setPositiveButton("OK") { dialog, id ->
                // Redirect to CompanyActivity
                val intent = Intent(this@PostJobActivity, JobsActivity::class.java)
                startActivity(intent)
                finish() // Finish current activity
            }
        val alert = dialogBuilder.create()
        alert.setTitle("Job Post Success")
        alert.show()
    }
}
