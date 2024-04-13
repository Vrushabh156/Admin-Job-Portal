package com.adminjob.adminjobapp.ui.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adminjob.adminjobapp.databinding.ActivityCompanyDetailBinding
import com.google.firebase.firestore.FirebaseFirestore

class CompanyDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCompanyDetailBinding
    private lateinit var firestore: FirebaseFirestore // Firestore instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompanyDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance()

        // Set an OnClickListener on the Register button
        binding.btnSubmit.setOnClickListener {
            saveCompanyInfo()
        }
    }

    private fun saveCompanyInfo() {
        val companyName = binding.etFirstName.text.toString().trim()
        val companyDescription = binding.etLastName.text.toString().trim()

        // Check for empty fields
        if (companyName.isEmpty() || companyDescription.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Create a HashMap to store company details
        val company = hashMapOf(
            "name" to companyName,
            "description" to companyDescription
        )

        // Add a new document with a generated ID to a collection named "companies"
        firestore.collection("companies")
            .add(company)
            .addOnSuccessListener { documentReference ->
                showSuccessDialog()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error adding details: ${e.message}", Toast.LENGTH_SHORT)
                    .show()
            }
    }

    private fun showSuccessDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("Company registered successfully")
            .setCancelable(false)
            .setPositiveButton("OK") { dialog, id ->
                // Redirect to CompanyActivity
                val intent = Intent(this@CompanyDetailActivity, CompanyActivity::class.java)
                startActivity(intent)
                finish() // Finish current activity
            }
        val alert = dialogBuilder.create()
        alert.setTitle("Registration Success")
        alert.show()
    }

}

