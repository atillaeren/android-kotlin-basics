package com.example.basicsproject

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.basicsproject.databinding.FragmentExtensionsBinding
import java.text.SimpleDateFormat
import java.util.*
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class ExtensionsFragment : Fragment() {
    private lateinit var binding: FragmentExtensionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExtensionsBinding.inflate(inflater, container, false)
        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //-----------------------------  Extensions  -------------------------------

        //"racecar".isPalindrome()

        //7.isPrime()

        //true.reverseBoolean()

        //calculateSumDates()
        //requireContext().db()

        //Date().calculateTwoDates("2023-02-24")

        //binding.textView.setSize()

        //binding.imageView.setBackground()

        //binding.button.setButtonBackColor()


    }


    private fun String.isPalindrome() : Boolean {
        if (this.reversed() == this) {
            println("$this is a palindrom")
            return true
        }
        println("$this is not a palindrom")
        return false

        /*
        var aString: String = ""
        readLine()?.let {
            println("Enter a String")
            aString = readln()
        }

        if (aString.reversed() == aString) {
            println("$aString is a palindrom")
            return true
        }
        println("$aString is not a palindrom")
        return false
         */
    }

    private fun Int.isPrime() : Boolean {
        if (this <= 1) {
            println("$this is not a prime number")
            return false
        }
        for (i in 2 until this) {
            if (this % i == 0) {
                println("$this is not a prime number")
                return false
            }
        }
        println("$this is a prime number")
        return true
    }

    private fun Boolean.reverseBoolean()  {
        val changedValue = !this
        println("Given value ($this) changed to ($changedValue)")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun Date.calculateTwoDates(aDate: String) {
        val sdf= SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = sdf.format(this.time)

        val period = Period.between(LocalDate.parse(date), LocalDate.parse(aDate))
        val numberOfDays = period.days

        println(numberOfDays)

    }

    fun TextView.setSize() {
        binding.textView.setOnClickListener {
            this.setTextSize(5,15.5f)
        }

    }

    fun ImageView.setBackground() {
        binding.imageView.setOnClickListener {
            this.setImageResource(R.drawable.ic_launcher_background)
        }

    }

    private fun Button.setButtonBackColor() {

        binding.button.setOnClickListener {
            val color = Color.rgb(85,155,0)
            this.setBackgroundColor(color)
        }

    }






}