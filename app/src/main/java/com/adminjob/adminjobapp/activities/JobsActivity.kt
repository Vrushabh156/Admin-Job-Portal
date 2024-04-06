package com.adminjob.adminjobapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adminjob.adminjobapp.databinding.ActivityJobsBinding
import com.adminjob.adminjobapp.databinding.ActivityLoginBinding

class JobsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJobsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}