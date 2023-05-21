package com.example.viewpagerproject


import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter(
    private val images: List<Int>,
    private val subtitles: List<String>,
    private val backgrounds: List<Int>
) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(val binding: ItemSlideBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view = ItemSlideBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val currentImage = images[position]
        val currentSubtitle = subtitles[position]
        val currentBackground = backgrounds[position]
        holder.binding.imageView.setImageResource(currentImage)
        holder.binding.subTitleTV.text = currentSubtitle
        holder.itemView.setBackgroundColor(currentBackground)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}