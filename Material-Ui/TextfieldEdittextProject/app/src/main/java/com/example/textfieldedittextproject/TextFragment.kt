package com.example.textfieldedittextproject

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.textfieldedittextproject.databinding.FragmentTextBinding
import com.google.android.material.slider.Slider


class TextFragment : Fragment() {
    private lateinit var binding : FragmentTextBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTextBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.editTextTextPersonName3.underline()



        binding.apply {
            phoneNumberET.paint?.isUnderlineText = true

            nameSurnameET.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    nameSurnameET.hideKeyboard()
                    true
                } else {
                    false
                }
            }



            emailET.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    val email = s.toString()
                    if (!isValidEmail(email)) {
                        emailET.error = "Invalid email format"
                    } else {
                        emailET.error = null
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }


    }

    private fun EditText.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(this.windowToken, 0)
    }


    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


    /*
    ----------------both ways working----------------

    private fun EditText.underline() {
        paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }
     */



}