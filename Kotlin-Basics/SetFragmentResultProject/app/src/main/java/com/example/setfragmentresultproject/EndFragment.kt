package com.example.setfragmentresultproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.setfragmentresultproject.databinding.FragmentEndBinding


class EndFragment : Fragment() {
    private lateinit var binding : FragmentEndBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEndBinding.inflate(inflater,container,false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textView.isVisible = false

        val timer = object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.textView.isVisible = true
            }

            override fun onFinish() {
                goPreviousFragment()
            }
        }
        timer.start()
    }

    private fun goPreviousFragment() {
        setFragmentResult(
            Messages.REQUEST_KEY,
            bundleOf(Messages.BUNDLE_KEY to Messages.message)
        )
        findNavController().navigateUp()
    }
}