package com.example.recyclerviewproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewproject.databinding.AppRecyclerRowBinding

class AppListRecyclerAdapter (private var data: ArrayList<AppDataClass>): RecyclerView.Adapter<AppListRecyclerAdapter.ViewHolder>()  {


    var onClick : ((AppDataClass)->Unit)? = null
    class ViewHolder (val binding: AppRecyclerRowBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AppRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.appName.text = data[position].appName
        holder.binding.appImage.setImageResource(data[position].appIcon)
        holder.itemView.setOnClickListener{
            onClick?.invoke(data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}