package com.adminjob.adminjobapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adminjob.adminjobapp.databinding.JobsListBinding
import com.adminjob.adminjobapp.models.Job

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
            }
        }
    }

    override fun getItemCount(): Int = jobsList.size
}