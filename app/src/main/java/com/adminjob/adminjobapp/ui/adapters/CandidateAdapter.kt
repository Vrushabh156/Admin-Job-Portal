package com.adminjob.adminjobapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adminjob.adminjobapp.databinding.CandidateItemBinding
import com.adminjob.adminjobapp.models.CandidateDetails

class CandidateAdapter(private val jobs: List<CandidateDetails>) :
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
            }
        }
    }

    override fun getItemCount(): Int = jobs.size
}