package com.example.firebaseproject

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.firebaseproject.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth


        binding.apply {
            registerBtn.setOnClickListener {
                signUpUser()
            }

            regToLogTV.setOnClickListener {
                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            val intent = Intent(requireActivity(), HomePageActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }

    private fun signUpUser() {

        binding.apply {
            val email = emailET.text.toString()
            val password = passwordET.text.toString()

            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(
                    requireContext(),
                    "Email or Password Can't Be Empty..",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(
                            requireContext(),
                            "Successfully Signed Up..",
                            Toast.LENGTH_SHORT
                        ).show()
                        findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToVerifyEmailFragment())
                    } else {
                        Toast.makeText(requireContext(), "Sign Up Failed..", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Please Enter Proper Email", Toast.LENGTH_SHORT)
                    .show()
            }


        }


    }
}