package com.example.sharedpreferencesproject

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.sharedpreferencesproject.databinding.FragmentSharedBinding

class SharedFragment : Fragment() {
    private lateinit var binding: FragmentSharedBinding

    //error without by lazy!!!
    private val sharedPreferences by lazy {
        requireContext().getSharedPreferences("places", Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSharedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(
            requireContext(),
            "Romance and adventure that awaits you on your journey, let's explore..",
            Toast.LENGTH_LONG
        ).show()

        binding.apply {
            btnSave.setOnClickListener {
                sharedPreferences.apply {
                    edit().apply {
                        val place = nameET.text.toString()
                        val visitCount = getInt(place, 0) + 1
                        putString("place_name",place)
                        putInt(place, visitCount)
                        putBoolean("visited_$place", true)
                        Toast.makeText(requireContext(), "$place saved..", Toast.LENGTH_SHORT).show()
                    }.apply()

                }
                clearScreen()
            }

            btnCheck.setOnClickListener {
                sharedPreferences.apply {
                    val place = nameET.text.toString()
                    val placeShared = getString("place_name",place)
                    val visitCount = getInt(place, 0)
                    val visited = getBoolean("visited_$place",false)

                    if (visitCount > 0) {
                        countET.setText(visitCount.toString())
                        checkBox.isChecked = visited
                        nameET.setText(placeShared)
                    } else {
                        clearScreen()
                        Toast.makeText(requireContext(), "Place not found!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun clearScreen() {
        binding.apply {
            countET.text.clear()
            checkBox.isChecked = false
        }
    }
}


