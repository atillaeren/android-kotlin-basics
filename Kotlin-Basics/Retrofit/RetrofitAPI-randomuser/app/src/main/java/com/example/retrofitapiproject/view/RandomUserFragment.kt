package com.example.retrofitapiproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.retrofitapiproject.databinding.FragmentRandomUserBinding
import com.example.retrofitapiproject.model.Repository
import com.example.retrofitapiproject.viewmodel.RandomUserViewModel
import com.example.retrofitapiproject.viewmodel.UserViewModelFactory

import kotlinx.coroutines.launch

class RandomUserFragment : Fragment() {

    private lateinit var binding: FragmentRandomUserBinding
    private val viewModel: RandomUserViewModel by viewModels {
        UserViewModelFactory(Repository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRandomUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.getUserBtn.setOnClickListener {
            responseUser()
        }
    }

    private fun responseUser() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.users.collect { users ->
                binding.userTV.text = users.joinToString("\n\n") { user ->
                    "Gender: ${user.Gender}\n\n" +
                            "Name: ${user.name.title} ${user.name.first} ${user.name.last}\n\n" +
                            "Location: ${user.location.street.name}, ${user.location.street.number}, ${user.location.city}," +
                            " ${user.location.state}, ${user.location.country}, ${user.location.timezone.description}," +
                            " ${user.location.timezone.offset}}\n\n" +
                            "Nationality: ${user.nat}"
                }
            }
        }
    }
}