package com.example.machinetestspark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.machinetestspark.databinding.ActivityMainBinding
import com.example.machinetestspark.databinding.FragmentSignupBinding
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