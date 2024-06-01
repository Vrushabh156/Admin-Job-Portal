package com.adminjob.adminjobapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.adminjob.adminjobapp.databinding.ActivityJobsBinding
import com.adminjob.adminjobapp.models.Job
import com.adminjob.adminjobapp.ui.adapters.JobsAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class JobsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJobsBinding
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this@JobsActivity, PostJobActivity::class.java)
            startActivity(intent)
        }

        fetchJobs()
    }

    private fun fetchJobs() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val userId = currentUser.uid
//            db.collection("users").document(userId).collection("Jobinformation").get()
            db.collection("Jobinformation").get()
                .addOnSuccessListener { result ->
                    val jobsList = mutableListOf<Job>()
                    for (document in result) {
                        val job = document.toObject(Job::class.java)
                        jobsList.add(job)
                    }
                    setupRecyclerView(jobsList)
                }.addOnFailureListener { exception ->
                    Toast.makeText(this, "Error fetching the data", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(this, "User not authenticated", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRecyclerView(jobsList: List<Job>) {
        val adapter = JobsAdapter(jobsList)
        binding.rvJobs.layoutManager = LinearLayoutManager(this)
        binding.rvJobs.adapter = adapter
    }
}
