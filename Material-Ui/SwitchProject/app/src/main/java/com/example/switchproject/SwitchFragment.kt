package com.example.switchproject

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.CompoundButton.OnCheckedChangeListener
import com.example.switchproject.databinding.FragmentSwitchBinding


class SwitchFragment : Fragment() {
    private lateinit var binding: FragmentSwitchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSwitchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            switchMaterial.isChecked = false
            switchMaterial.thumbTintList = ColorStateList.valueOf(Color.BLACK)
            switchMaterial.setOnCheckedChangeListener { _, _ ->
                if (switchMaterial.isChecked) {
                    this@SwitchFragment.view?.setBackgroundColor(Color.RED)
                } else {
                    this@SwitchFragment.view?.setBackgroundColor(Color.GREEN)
                }
            }


        }
    }
}