package com.example.pickerproject

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.example.pickerproject.databinding.FragmentAgePickBinding
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter


class AgePickFragment : Fragment(), DatePickerDialog.OnDateSetListener{

    private lateinit var binding: FragmentAgePickBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAgePickBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.button5.setOnClickListener {
            val datePickerDialog = DatePickerDialog(requireActivity(), this@AgePickFragment, 2000, 0, 1)
            datePickerDialog.show()
        }

        binding.button4.setOnClickListener {
            findNavController().navigate(AgePickFragmentDirections.actionAgePickFragmentToMainPageFragment())
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val birthDate = LocalDate.of(year, month + 1, dayOfMonth)
        val currentDate = LocalDate.now()
        val age = calculateAge(birthDate, currentDate)
        PickerObject.obj.age = age
        println(PickerObject.obj.age)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculateAge(birthDate: LocalDate, currentDate: LocalDate): Int {
        return Period.between(birthDate, currentDate).years
    }


}