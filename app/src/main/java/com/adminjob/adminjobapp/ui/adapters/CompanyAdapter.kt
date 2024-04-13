package com.adminjob.adminjobapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adminjob.adminjobapp.models.Company
import com.adminjob.adminjobapp.R

class CompanyAdapter(private val companyList: List<Company>) :
    RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder>() {

    class CompanyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var companyName: TextView = itemView.findViewById(R.id.company_nameTV)
        var companyDescription: TextView = itemView.findViewById(R.id.company_descriptionTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.company_list, parent, false)
        return CompanyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        val currentItem = companyList[position]
        holder.companyName.text = currentItem.name
        holder.companyDescription.text = currentItem.description
    }

    override fun getItemCount() = companyList.size
}