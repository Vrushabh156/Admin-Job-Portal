package com.adminjob.adminjobapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adminjob.adminjobapp.databinding.ActivityCompanyBinding

class CompanyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCompanyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompanyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this@CompanyActivity, CompanyDetailActivity::class.java)
            startActivity(intent)
        }
    }
}