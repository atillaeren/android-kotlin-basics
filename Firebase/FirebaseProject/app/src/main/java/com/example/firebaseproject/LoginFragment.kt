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
import com.example.firebaseproject.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        binding.apply {
            loginBtn.setOnClickListener {
                signInUser()
            }

            backSignUpBtn.setOnClickListener {
                findNavController().navigateUp()
            }
            forgotPasswordBtn.setOnClickListener {
                val email = emailET.text.toString()
                if (email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    auth.sendPasswordResetEmail(email)
                }

            }
        }
    }

    private fun signInUser() {
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
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(
                            requireContext(),
                            "Successfully Signed In..",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(requireActivity(),HomePageActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(requireContext(), "Sign In Failed..", Toast.LENGTH_SHORT)
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