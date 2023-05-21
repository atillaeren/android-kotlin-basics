package com.example.lottieproject

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.lottieproject.databinding.ActivityMainBinding
import com.example.lottieproject.databinding.ActivitySplashScreenBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.apply {
            button3.setOnClickListener {
                lottieAnimationView.isVisible = true
                lottieAnimationView.playAnimation()
                lottieAnimationView.addAnimatorListener(object : AnimatorListener{
                    override fun onAnimationStart(p0: Animator) {

                    }

                    override fun onAnimationEnd(p0: Animator) {
                        lottieAnimationView.isVisible = false
                    }

                    override fun onAnimationCancel(p0: Animator) {

                    }

                    override fun onAnimationRepeat(p0: Animator) {

                    }
                })
            }


            button.setOnClickListener {
                lottieAnimationView.pauseAnimation()
            }

            button2.setOnClickListener {
                lottieAnimationView.resumeAnimation()
            }
        }


    }
}