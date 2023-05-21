package com.example.searchbarproject

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchbarproject.databinding.ItemNameSurnameBinding
import kotlin.collections.ArrayList

class SearchAdapter( var data: ArrayList<SearchDataClass>) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemNameSurnameBinding): RecyclerView.ViewHolder(binding.root) {

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNameSurnameBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)


    }



    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.textViewName.text = data[position].name
        holder.binding.textViewSurname.text = " " + data[position].surName


    }

    override fun getItemCount(): Int {
        return data.size
    }


}