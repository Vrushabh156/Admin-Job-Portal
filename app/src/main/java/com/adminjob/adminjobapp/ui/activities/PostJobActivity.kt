package com.adminjob.adminjobapp.ui.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adminjob.adminjobapp.databinding.ActivityPostJobBinding
import com.google.firebase.firestore.FirebaseFirestore

class PostJobActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostJobBinding
    private val db = FirebaseFirestore.getInstance()

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
        val jobName = binding.etLastName.text.toString().trim()
        val jobStartDate =
            binding.etEmail.text.toString().trim()
        val jobSummary =
            binding.etContact.text.toString().trim()

        val jobDescription = binding.etPassword.text.toString()
            .trim()
        val jobPosition = binding.etSummary.text.toString()
            .trim()
        val numberOfVacancy = binding.etPosition.text.toString()
            .trim()
        val jobCategory = binding.etVacancy.text.toString()
            .trim()
        val platform = binding.etPlatform.text.toString().trim()
        val minExperience = binding.etMinExp.text.toString().trim()
        val maxExperience = binding.etMaxExp.text.toString().trim()

        // Check if any field is empty
        if (jobCompany.isEmpty() || jobName.isEmpty() || jobStartDate.isEmpty() || jobSummary.isEmpty() ||
            jobDescription.isEmpty() || jobPosition.isEmpty() || numberOfVacancy.isEmpty() || jobCategory.isEmpty() ||
            platform.isEmpty() || minExperience.isEmpty() || maxExperience.isEmpty()
        ) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Create a HashMap to store all the job details
        val job = hashMapOf(
            "company" to jobCompany,
            "name" to jobName,
            "startDate" to jobStartDate,
            "summary" to jobSummary,
            "description" to jobDescription,
            "position" to jobPosition,
            "vacancy" to numberOfVacancy,
            "category" to jobCategory,
            "platform" to platform,
            "minExperience" to minExperience,
            "maxExperience" to maxExperience
        )

        // Add the job to the "jobs" collection in Firestore
        db.collection("jobs")
            .add(job)
            .addOnSuccessListener {
                showSuccessDialog()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error adding job: ${e.message}", Toast.LENGTH_SHORT).show()
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