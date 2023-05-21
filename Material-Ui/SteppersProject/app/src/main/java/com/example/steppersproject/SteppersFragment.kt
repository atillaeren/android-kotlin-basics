package com.example.steppersproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.steppersproject.databinding.FragmentSteppersBinding


class SteppersFragment : Fragment() {
    private lateinit var binding:FragmentSteppersBinding

    private var result = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSteppersBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.apply {
            imageButton1.setOnClickListener {
                if (result > 0) {
                    result -=5
                    textView1.text = result.toString()
                }
            }
            imageButton2.setOnClickListener {
                if (result<50) {
                    result +=5
                    textView1.text = result.toString()
                }
            }
            imageButton3.setOnClickListener {
                if (result > 0) {
                    result -=5
                    textView2.text = result.toString()
                }
            }
            imageButton4.setOnClickListener {
                if (result<50) {
                    result +=5
                    textView2.text = result.toString()
                }
            }
            imageButton5.setOnClickListener {
                if (result > 0) {
                    result -=5
                    textView3.text = result.toString()
                }
            }
            imageButton6.setOnClickListener {
                if (result<50) {
                    result +=5
                    textView3.text = result.toString()
                }
            }
        }
    }


}