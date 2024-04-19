package com.adminjob.adminjobapp.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adminjob.adminjobapp.databinding.JobsListBinding
import com.adminjob.adminjobapp.models.Job
import com.adminjob.adminjobapp.ui.activities.JobAppliedCandidateActivity

class JobsAdapter(private val jobsList: List<Job>) :
    RecyclerView.Adapter<JobsAdapter.JobViewHolder>() {

    class JobViewHolder(val binding: JobsListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val binding = JobsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobViewHolder, positions: Int) {
        with(holder) {
            with(jobsList[positions]) {
                binding.jobNameTV.text = Company
                binding.startDate.text = Position
                binding.positionTxt.text = Salary
                binding.lastDate11.text = LastDate

                binding.viewJobsButton.setOnClickListener {
                    val context = holder.itemView.context
                    val intent = Intent(context, JobAppliedCandidateActivity::class.java)
                    // You can also pass data to the CandidateActivity if needed
                    // intent.putExtra("some_key", "some_value")
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount(): Int = jobsList.size
}