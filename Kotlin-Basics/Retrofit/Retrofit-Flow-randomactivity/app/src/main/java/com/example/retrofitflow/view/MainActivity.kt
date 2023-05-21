package com.example.retrofitflow.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.retrofitflow.viewmodel.RandomViewModel
import com.example.retrofitflow.viewmodel.RandomViewModelFactory
import com.example.retrofitflow.model.Repository
import com.example.retrofitflow.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: RandomViewModel by viewModels{
        RandomViewModelFactory(Repository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.getUserBtn.setOnClickListener {
            responseUser()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun responseUser() {
        lifecycleScope.launch {
            viewModel.activity.collect { activity ->
                binding.activityTV.text = activity.body()?.let {
                    "Activity: ${it.activity}\n\n" +
                            "Type: ${it.type}\n\n" +
                            "Participants: ${it.participants}\n\n" +
                            "Price: ${it.price}\n\n" +
                            "Accessibility: ${it.accessibility}\n\n"
                }
            }
        }
    }
}