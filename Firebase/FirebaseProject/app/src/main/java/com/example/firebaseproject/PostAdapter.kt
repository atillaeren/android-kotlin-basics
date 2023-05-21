package com.example.firebaseproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firebaseproject.databinding.PostItemBinding

class PostAdapter(private val photoList: List<PostItem>) :
    RecyclerView.Adapter<PostAdapter.PhotoViewHolder>() {
    private lateinit var binding: PostItemBinding

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {

        binding = PostItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PhotoViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photoItem = photoList[position]

        fun bind(postItem: PostItem) {
            Glide.with(binding.photoImageView.context)
                .load(postItem.photoUrl)
                .into(binding.photoImageView)


            binding.uploaderUidTextView.text = postItem.uploaderUid

            binding.goCommentButton.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToPostCommentFragment(uid = postItem.photoUrl)
                holder.itemView.findNavController().navigate(action)
            }
        }

        bind(photoItem)
    }

    override fun getItemCount(): Int {
        return photoList.size
    }
}
