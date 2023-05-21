package com.example.buttonsproject

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import com.example.buttonsproject.databinding.FragmentButtonsBinding


class ButtonsFragment : Fragment() {
    private lateinit var binding: FragmentButtonsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentButtonsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            saloonBtn.apply {
                setBackgroundResource(R.drawable.ic_launcher_background)
                setPadding(16, 16, 16, 16)
                textAlignment = View.TEXT_ALIGNMENT_CENTER
                setShadowLayer(5f, 5f, 5f, Color.GRAY)
                setBackgroundColor(Color.RED)
                alpha = 0.5f
                setTextColor(Color.WHITE)
                setOnClickListener {
                    callSaloonButton()
                }
            }

        }
        callSheriffButton()

        callBlackSmithButton()

        callVigilanteButton()

        callRobberyButton()


    }

    private fun callSheriffButton() {
        val sheriff = binding.sheriffBtn
        val options = listOf("Call for Backup", "Warn the Town")

        sheriff.setOnClickListener {
            val popup = PopupMenu(requireContext(), sheriff)
            options.forEach { option ->
                popup.menu.add(option)
            }
            popup.setOnMenuItemClickListener { item ->
                when (item.title) {
                    "Call for Backup" -> Toast.makeText(
                        requireContext(),
                        "Backup Called..",
                        Toast.LENGTH_SHORT
                    ).show()
                    "Warn the Town" -> Toast.makeText(
                        requireContext(),
                        "Town Warned..",
                        Toast.LENGTH_SHORT
                    ).show()
                    else -> { /* do nothing */
                    }
                }
                true
            }
            popup.show()
        }
    }

    private fun callSaloonButton() {
        val saloon = binding.saloonBtn
        val options2 = listOf("Chicken Wings with Beer", "Beef Wellington")

        saloon.setOnClickListener {
            val popup = PopupMenu(requireContext(), saloon)
            options2.forEach { option ->
                popup.menu.add(option)
            }
            popup.setOnMenuItemClickListener { item ->
                when (item.title) {
                    "Chicken Wings with Beer" -> Toast.makeText(
                        requireContext(),
                        "Chickens getting ready..",
                        Toast.LENGTH_SHORT
                    ).show()
                    "Beef Wellington" -> Toast.makeText(
                        requireContext(),
                        "Wellington is on the way..",
                        Toast.LENGTH_SHORT
                    ).show()
                    else -> { /* do nothing */
                    }
                }
                true
            }
            popup.show()
        }
    }

    private fun callBlackSmithButton() {
        binding.apply {
            blackSmith2.isEnabled = false

            blackSmith1.setOnClickListener {
                if (blackSmith2.isEnabled) {
                    blackSmith2.isEnabled = false
                    blackSmith2.text = "Blacksmith Closed"
                } else {
                    blackSmith2.isEnabled = true
                    blackSmith2.text = "Blacksmith Opened"
                }
            }
            blackSmith2.setOnClickListener {
                Toast.makeText(requireContext(), "Back to work..", Toast.LENGTH_SHORT).show()
            }
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    private fun callVigilanteButton() {
        val button = binding.vigilanteBtn

        button.setOnTouchListener { _, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    button.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(requireContext(), R.color.yellow)
                    )
                }
                MotionEvent.ACTION_UP -> {
                    button.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(requireContext(), R.color.purple_500)
                    )
                }
            }
            true
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun callRobberyButton() {
        val soundPool = SoundPool.Builder().setMaxStreams(1).build()
        val soundId = soundPool.load(context, R.raw.shake, 1)
        binding.robberBtn.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.robberBtn.startAnimation(
                        AnimationUtils.loadAnimation(
                            context,
                            R.anim.shake
                        )
                    )
                    binding.robberBtn.setImageResource(R.drawable.robbery_highlighted)

                    soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
                        soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
                    }
                    true
                }
                MotionEvent.ACTION_UP -> {
                    binding.robberBtn.setImageResource(R.drawable.robbery)
                    true
                }
                else -> false
            }
        }
    }

}