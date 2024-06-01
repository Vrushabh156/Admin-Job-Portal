package com.adminjob.adminjobapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.adminjob.adminjobapp.databinding.ActivityJobAppliedCandidateBinding
import com.adminjob.adminjobapp.models.CandidateDetails
import com.adminjob.adminjobapp.ui.adapters.CandidateAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore


class JobAppliedCandidateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJobAppliedCandidateBinding
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobAppliedCandidateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firestore = FirebaseFirestore.getInstance()
        fetchJobs()
    }

    private fun fetchJobs() {
        firestore.collection("job_applications").addSnapshotListener { snapshot, e ->
            if (e != null) {
                // Handle error
                return@addSnapshotListener
            }

            val jobs = mutableListOf<CandidateDetails>()
            snapshot?.documents?.forEach {
                val job = it.toObject(CandidateDetails::class.java)
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
    }

    private fun showAcceptDialog(candidate: CandidateDetails) {
        AlertDialog.Builder(this)
            .setTitle("Accept Candidate")
            .setMessage("Are you sure you want to accept ${candidate.fullName}?")
            .setPositiveButton("Accept") { dialog, which ->
                // Ensure jobId is not null or empty
                candidate.CompanyjobId.takeIf { it.isNotEmpty() }?.let { CompanyjobId ->
                    firestore.collection("job_applications").document(CompanyjobId)
                        .update("Message", "Accepted") // Assuming the field to update is named "status"
                        .addOnSuccessListener {
                            Snackbar.make(
                                binding.root,
                                "${candidate.fullName} accepted",
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                        .addOnFailureListener { e ->
                            Snackbar.make(
                                binding.root,
                                "Failed to update status for ${candidate.fullName}: ${e.message}",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                } ?: run {
                    Snackbar.make(
                        binding.root,
                        "Job ID is missing for ${candidate.fullName}",
                        Snackbar.LENGTH_LONG
                    ).show()
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
                candidate.CompanyjobId.takeIf { it.isNotEmpty() }?.let { CompanyjobId ->
                    firestore.collection("job_applications").document(CompanyjobId)
                        .update("Message", "Rejected") // Assuming the field to update is named "status"
                        .addOnSuccessListener {
                            Snackbar.make(
                                binding.root,
                                "${candidate.fullName} rejected",
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                        .addOnFailureListener { e ->
                            Snackbar.make(
                                binding.root,
                                "Failed to update status for ${candidate.fullName}: ${e.message}",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                } ?: run {
                    Snackbar.make(
                        binding.root,
                        "Job ID is missing for ${candidate.fullName}",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

//    private fun sendEmail(recipient: String, subject: String, body: String) {
//        // Use Kotlin Coroutines for network call on a background thread
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                MailUtil.sendEmail(recipient, subject, body)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }
}
