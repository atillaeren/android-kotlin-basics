package com.example.setfragmentresultproject

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.setfragmentresultproject.databinding.FragmentStartBinding


class StartFragment : Fragment() {
    private lateinit var binding: FragmentStartBinding

    private var countDownTimer: CountDownTimer? = null
    


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            messageTV.isVisible = false
            editText.isVisible = false
            countDownTV.isVisible = false

            button.setOnClickListener {
                editText.isVisible = true
                button.text = "Check"

                if (editText.text.toString() == Messages.code) {
                    editText.isVisible = false
                    countDownTV.isVisible = true
                    button.isVisible = false
                    navigateToEndFragment()
                }
            }
        }
        setFragmentListener()
    }

    private fun setFragmentListener() {
        setFragmentResultListener(Messages.REQUEST_KEY) { _, bundle ->
            val result = bundle.getString(Messages.BUNDLE_KEY)

            val timer = object : CountDownTimer(3000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    binding.messageTV.isVisible = true
                    binding.messageTV.text = "Message is $result"
                    binding.button.isVisible = false
                }

                override fun onFinish() {
                    binding.messageTV.isVisible = false
                }
            }
            timer.start()
        }
    }


    private fun navigateToEndFragment() {
        countDownTimer = object : CountDownTimer(15000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.countDownTV.text =
                    "Message decrypting.. Will destroy in: ${millisUntilFinished / 1000}"
                if (millisUntilFinished <= 5000) {
                    countDownTimer?.cancel()
                    findNavController().navigate(StartFragmentDirections.actionStartFragmentToEndFragment())
                }
            }
            override fun onFinish() {}
        }
        countDownTimer?.start()
    }
}