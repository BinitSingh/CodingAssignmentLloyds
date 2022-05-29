package com.demo.codingassignmentlloyds.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demo.codingassignmentlloyds.R
import com.demo.codingassignmentlloyds.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}