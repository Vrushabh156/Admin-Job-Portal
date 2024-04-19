package com.adminjob.adminjobapp.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adminjob.adminjobapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardView1.setOnClickListener {
            val intent = Intent(this@MainActivity, CompanyActivity::class.java)
            startActivity(intent)
        }

        binding.cardView2.setOnClickListener {
            val intent = Intent(this@MainActivity, JobAppliedCandidateActivity::class.java)
            startActivity(intent)
        }

        binding.cardView3.setOnClickListener {
            val intent = Intent(this@MainActivity, ProfileActivity::class.java)
            startActivity(intent)
        }

        binding.cardView4.setOnClickListener {
            // Log out from Firebase
            FirebaseAuth.getInstance().signOut()

            // Redirect to Login Activity or any other Activity after sign out
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            // Clear the activity stack to prevent the user from going back to the MainActivity after logging out
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}