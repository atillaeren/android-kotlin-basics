package com.example.progressbarproject

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.example.progressbarproject.databinding.FragmentProgressBarBinding
import kotlinx.coroutines.*


class ProgressBarFragment : Fragment() {
    private lateinit var binding: FragmentProgressBarBinding
    private val externalScope: CoroutineScope = GlobalScope


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProgressBarBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            button.setOnClickListener {
                progressBar.visibility = View.VISIBLE
                button.visibility = View.INVISIBLE
                progressBar.indeterminateDrawable.colorFilter =
                    BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                        Color.RED,
                        BlendModeCompat.SRC_ATOP
                    )
                progressBar.max = 100

                externalScope.launch(Dispatchers.Main) {
                    for (progress in 0..100) {
                        delay(100)
                        progressBar.progress = progress
                        textView.text = progress.toString() + "/" + progressBar.max

                        if (progress % 10 == 0) {
                            progressBar.indeterminateDrawable.colorFilter =
                                BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                                    when (progress) {
                                        0 -> Color.RED
                                        10 -> Color.YELLOW
                                        20 -> Color.GREEN
                                        30 -> Color.BLUE
                                        40 -> Color.MAGENTA
                                        50 -> Color.CYAN
                                        60 -> Color.RED
                                        70 -> Color.YELLOW
                                        80 -> Color.GREEN
                                        90 -> Color.BLUE
                                        else -> Color.MAGENTA
                                    }, BlendModeCompat.SRC_ATOP
                                )
                            if (progress == 100) {
                                textView.text = "0"
                                progressBar.visibility = View.INVISIBLE
                                button.visibility = View.VISIBLE
                            }
                        }
                    }
                }
            }
        }
    }
}