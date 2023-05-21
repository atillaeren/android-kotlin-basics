package com.example.materialtogglegroupproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.materialtogglegroupproject.databinding.FragmentMaterialBinding


class MaterialFragment : Fragment() {

    private lateinit var binding: FragmentMaterialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMaterialBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            toggleButtonGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
                if (isChecked) {
                    when(checkedId) {
                        R.id.nApps2023 -> {
                            nApps2023.scaleX = 1.2F
                            nApps2023.scaleY = 1.2F
                        }
                        R.id.neon -> {
                            neon.scaleX = 0.9F
                            neon.scaleY = 0.9F
                        }
                        R.id.apps -> {
                            apps.scaleX = 0.6F
                            apps.scaleY = 0.6F
                        }
                    }
                } else {
                    when(checkedId) {
                        R.id.nApps2023 -> {
                            nApps2023.scaleX = 1F
                            nApps2023.scaleY = 1F
                        }
                        R.id.neon -> {
                            neon.scaleX = 1F
                            neon.scaleY = 1F
                        }
                        R.id.apps -> {
                            apps.scaleX = 1F
                            apps.scaleY = 1F
                        }
                    }
                }
            }
        }

    }

}