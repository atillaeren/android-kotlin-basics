package com.example.firebaseproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseproject.databinding.CommentItemBinding

class CommentAdapter(private var commentList: List<CommentItem>) :
    RecyclerView.Adapter<CommentAdapter.CommentViewHolder>(){

    private lateinit var binding : CommentItemBinding

    class CommentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        binding = CommentItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CommentViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val commentList = commentList[position]

        fun bind(commentItem: CommentItem) {

            binding.uidTV.text = commentItem.commentUid
            binding.commentTV.text = commentItem.comment
        }
        bind(commentList)

    }
    override fun getItemCount(): Int {
        return commentList.size
    }
}