package com.adminjob.adminjobapp.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adminjob.adminjobapp.R
import com.adminjob.adminjobapp.databinding.ActivityCompanyBinding
import com.adminjob.adminjobapp.databinding.ActivityJobsBinding

class JobsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJobsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}