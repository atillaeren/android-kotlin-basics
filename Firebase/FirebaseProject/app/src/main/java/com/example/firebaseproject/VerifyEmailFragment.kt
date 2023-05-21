package com.example.firebaseproject

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.firebaseproject.databinding.FragmentVerifyEmailBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class VerifyEmailFragment : Fragment() {
    private lateinit var binding: FragmentVerifyEmailBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVerifyEmailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        binding.sendVerifyLinkBtn.setOnClickListener {
            sendVerifyEmail()
        }
        binding.button3.setOnClickListener {
            Toast.makeText(requireContext(), "Please verify your email..", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun sendVerifyEmail() {
        binding.apply {
            val email = verifyEmailET.text.toString()
            if (Patterns.EMAIL_ADDRESS.matcher(email)
                    .matches() && email == auth.currentUser?.email
            ) {
                auth.currentUser?.sendEmailVerification()
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(requireContext(), "Email Sent..", Toast.LENGTH_SHORT)
                                .show()
                            binding.button3.setOnClickListener {
                                findNavController().navigateUp()
                            }
                        } else {
                            //
                        }
                    }
            } else
                Toast.makeText(requireContext(), "Email Not Matches..", Toast.LENGTH_SHORT).show()
        }
    }
}