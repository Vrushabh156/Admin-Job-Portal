package com.adminjob.adminjobapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adminjob.adminjobapp.databinding.ActivityPostJobBinding

class PostJobActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostJobBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostJobBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}