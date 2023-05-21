package com.example.roomproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.roomproject.databinding.FragmentGetInfoBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class GetInfoFragment : Fragment() {
    private lateinit var binding: FragmentGetInfoBinding
    private lateinit var viewModel: InfoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGetInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[InfoViewModel::class.java]

        binding.apply {

            saveBtn.setOnClickListener {
                val name = nameET.text.toString()
                val surname = surNameET.text.toString()
                val age = ageET.text.toString()
                val email = emailET.text.toString()

                val user = Information(personName = name, personSurname = surname, personAge = age, personEmail = email)
                viewModel.insert(user)
            }
            get.setOnClickListener {
                viewModel.allUsers.observe(requireActivity()) { users ->
                    users.forEach {
                        println(it.uuid.toString() + " " + it.personName + " " + it.personSurname + " " + it.personAge + " " + it.personEmail)
                    }
                }
            }
        }
    }
}