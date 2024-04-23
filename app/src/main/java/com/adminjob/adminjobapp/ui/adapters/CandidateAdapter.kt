package com.adminjob.adminjobapp.ui.adapters

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adminjob.adminjobapp.databinding.CandidateItemBinding
import com.adminjob.adminjobapp.models.CandidateDetails

class CandidateAdapter(
    private val jobs: List<CandidateDetails>,
    private val onAccept: (CandidateDetails) -> Unit,
    private val onReject: (CandidateDetails) -> Unit
) :
    RecyclerView.Adapter<CandidateAdapter.JobViewHolder>() {

    class JobViewHolder(val binding: CandidateItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CandidateItemBinding.inflate(layoutInflater, parent, false)
        return JobViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        with(holder) {
            with(jobs[position]) {
                binding.companyNameTV.text = fullName
                binding.companyDescriptionTV.text = company
                binding.viewJobsButton.setOnClickListener {
                    // Pass the context along with the CandidateDetails object
                    showDetailsDialog(this, holder.binding.root.context)
                }
                // Set click listener for accept button
                binding.editButton.setOnClickListener {
                    onAccept(this) // this refers to the current CandidateDetails object
                }
                // Set click listener for reject button
                binding.editButton12.setOnClickListener {
                    onReject(this) // this refers to the current CandidateDetails object
                }
            }
        }
    }

    // Modified showDetailsDialog function to accept Context as a parameter
    private fun showDetailsDialog(candidate: CandidateDetails, context: Context) {
        val message = buildString {
            append("Name: ${candidate.fullName}\n")
            append("Company: ${candidate.company}\n")
            append("Position: ${candidate.position}\n")
            append("Experience: ${candidate.experience}\n")
            append("Skills: ${candidate.skills}\n")
            append("Email: ${candidate.email}\n")
            append("Phone: ${candidate.phoneNumber}\n")
        }

        AlertDialog.Builder(context)
            .setTitle("${candidate.fullName}'s Details")
            .setMessage(message)
            .setPositiveButton("Close", null)
            .show()
    }

    override fun getItemCount(): Int = jobs.size
}
