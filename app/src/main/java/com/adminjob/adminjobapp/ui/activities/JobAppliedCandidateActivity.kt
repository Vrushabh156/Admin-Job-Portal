package com.adminjob.adminjobapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.adminjob.adminjobapp.databinding.ActivityJobAppliedCandidateBinding
import com.adminjob.adminjobapp.models.CandidateDetails
import com.adminjob.adminjobapp.ui.adapters.CandidateAdapter
import com.google.firebase.database.*

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
                binding.rvJobs.adapter = CandidateAdapter(jobs)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }
}