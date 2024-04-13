package com.adminjob.adminjobapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adminjob.adminjobapp.databinding.ActivityCompanyBinding
import com.google.firebase.firestore.FirebaseFirestore
import androidx.recyclerview.widget.LinearLayoutManager
import com.adminjob.adminjobapp.models.Company
import com.adminjob.adminjobapp.ui.adapters.CompanyAdapter

class CompanyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCompanyBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var companyList: MutableList<Company>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompanyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this@CompanyActivity, CompanyDetailActivity::class.java)
            startActivity(intent)
        }

        firestore = FirebaseFirestore.getInstance()
        companyList = mutableListOf()

        binding.rvJobs.layoutManager = LinearLayoutManager(this)
        fetchCompanies()
    }

    private fun fetchCompanies() {
        firestore.collection("companies").get().addOnSuccessListener { result ->
            for (document in result) {
                val company = document.toObject(Company::class.java)
                companyList.add(company)
            }
            binding.rvJobs.adapter = CompanyAdapter(companyList) { company ->
                val intent = Intent(this@CompanyActivity, JobsActivity::class.java)
                startActivity(intent)
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(this, "Error to fetch the data", Toast.LENGTH_SHORT).show()
        }
    }
}



