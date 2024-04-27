package com.adminjob.adminjobapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.adminjob.adminjobapp.databinding.ActivityJobAppliedCandidateBinding
import com.adminjob.adminjobapp.models.CandidateDetails
import com.adminjob.adminjobapp.ui.adapters.CandidateAdapter
import com.google.firebase.database.*
import android.app.AlertDialog
import android.widget.Toast

class JobAppliedCandidateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJobAppliedCandidateBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobAppliedCandidateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("applications")
        fetchJobs()
    }

    private fun fetchJobs() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val jobs = mutableListOf<CandidateDetails>()
                snapshot.children.forEach {
                    val job = it.getValue(CandidateDetails::class.java)
                    job?.let { jobs.add(it) }
                }
                binding.rvJobs.layoutManager = LinearLayoutManager(this@JobAppliedCandidateActivity)
                binding.rvJobs.adapter = CandidateAdapter(jobs,
                    onAccept = { candidate ->
                        // Handle accept action, show accept dialog
                        showAcceptDialog(candidate)
                    },
                    onReject = { candidate ->
                        // Handle reject action, show reject dialog
                        showRejectDialog(candidate)
                    })
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    private fun showAcceptDialog(candidate: CandidateDetails) {
        AlertDialog.Builder(this)
            .setTitle("Accept Candidate")
            .setMessage("Are you sure you want to accept ${candidate.fullName}?")
            .setPositiveButton("Accept") { dialog, which ->
                // Update the candidate status in the database to "Accepted"
                candidate.jobId.takeIf { it.isNotEmpty() }?.let { jobId ->
                    database.child(jobId).child("applicationReviewStatus").setValue("Accepted")
                        .addOnSuccessListener {
                            Toast.makeText(this, "${candidate.fullName} accepted", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "Failed to update status for ${candidate.fullName}", Toast.LENGTH_SHORT).show()
                        }
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showRejectDialog(candidate: CandidateDetails) {
        AlertDialog.Builder(this)
            .setTitle("Reject Candidate")
            .setMessage("Are you sure you want to reject ${candidate.fullName}?")
            .setPositiveButton("Reject") { dialog, which ->
                // Update the candidate status in the database to "Rejected"
                candidate.jobId.takeIf { it.isNotEmpty() }?.let { jobId ->
                    database.child(jobId).child("applicationReviewStatus").setValue("Rejected")
                        .addOnSuccessListener {
                            Toast.makeText(this, "${candidate.fullName} rejected", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "Failed to update status for ${candidate.fullName}", Toast.LENGTH_SHORT).show()
                        }
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}