package com.example.retrofitapiproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofitapiproject.viewmodel.RandomUserViewModel
import com.example.retrofitapiproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}