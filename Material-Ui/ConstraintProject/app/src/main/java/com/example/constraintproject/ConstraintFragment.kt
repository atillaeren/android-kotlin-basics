package com.example.constraintproject

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.constraintproject.databinding.FragmentConstraintBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class ConstraintFragment : Fragment() {
    private lateinit var binding: FragmentConstraintBinding
    val green = Color.rgb(0, 255, 0)
    val red = Color.rgb(255, 0, 0)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConstraintBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            val date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            dateTV.text = "Date: $date"

            button.setOnClickListener {
                button.text = "ON"
                refStatus.setTextColor(green)
            }

            button2.setOnClickListener {
                button2.text = "ON"
                tvStatus.setTextColor(green)
            }

            closeButton.setOnClickListener {
                button.text = "OFF"
                button2.text = "OFF"
                refStatus.setTextColor(red)
                tvStatus.setTextColor(red)
            }
        }
    }
}