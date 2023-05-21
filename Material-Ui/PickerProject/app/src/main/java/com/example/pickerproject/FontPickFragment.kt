package com.example.pickerproject

import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.findNavController
import com.example.pickerproject.databinding.FragmentFontPickBinding


class FontPickFragment : Fragment() {
    private lateinit var binding: FragmentFontPickBinding

    private val fonts = listOf(
        R.font.akaya_telivigala,
        R.font.germania_one,
        R.font.life_savers_bold,
        R.font.shadows_into_light
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFontPickBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val popup = PopupMenu(requireContext(), binding.textView3)
        fonts.forEach { option ->
            popup.menu.add(option)
        }



        binding.textView3.setOnClickListener {
            popup.setOnMenuItemClickListener { item ->
                when (item.title) {
                    "res/font/akaya_telivigala.ttf" -> {
                        PickerObject.obj.font = fonts[0]
                        binding.textView3.typeface =
                            ResourcesCompat.getFont(requireContext(), PickerObject.obj.font)
                    }

                    "res/font/germania_one.ttf" -> {
                        PickerObject.obj.font = fonts[1]
                        binding.textView3.typeface =
                            ResourcesCompat.getFont(requireContext(), PickerObject.obj.font)
                    }

                    "res/font/life_savers_bold" -> {
                        PickerObject.obj.font = fonts[2]
                        binding.textView3.typeface =
                            ResourcesCompat.getFont(requireContext(), PickerObject.obj.font)
                    }

                    "res/font/shadows_into_light" -> {
                        PickerObject.obj.font = fonts[3]
                        binding.textView3.typeface =
                            ResourcesCompat.getFont(requireContext(), PickerObject.obj.font)
                    }
                    else -> {}
                }
                true
            }
            popup.show()
        }

        binding.button3.setOnClickListener {
            PickerObject.obj.name = binding.nameET.text.toString()
            findNavController().navigate(FontPickFragmentDirections.actionFontPickFragmentToAgePickFragment())
        }

    }


}