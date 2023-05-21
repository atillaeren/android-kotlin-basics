package com.example.firebaseproject

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebaseproject.databinding.FragmentPostCommentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class PostCommentFragment : Fragment() {
    private lateinit var binding: FragmentPostCommentBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var commentAdapter: CommentAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostCommentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(activity)

            auth = Firebase.auth
            val commentItemList = getCommentsFromFirestore()
            commentAdapter = CommentAdapter(commentItemList)

            recyclerView.adapter = commentAdapter

            binding.button.setOnClickListener {
                putComment()
                val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                commentAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun putComment() {
        val url = arguments?.getString("uid")
        val db = Firebase.firestore

        val postCollection = db.collection("photos")
        val commentItemList = mutableListOf<CommentItem>()

        postCollection.get().addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot.documents) {
                val deger = document.getString("url")
                if (url == deger) {
                    val photoId = document.id
                    // Add a comment to the photo's comments subcollection
                    val comment = binding.commentET.text.toString()
                    val userId = FirebaseAuth.getInstance().currentUser?.uid
                    userId?.let {
                        val newComment = hashMapOf(
                            "userUid" to userId,
                            "comment" to comment
                        )
                        db.collection("photos/$photoId/comments")
                            .add(newComment)
                            .addOnSuccessListener {
                                // Create a new CommentItem and add it to the commentItemList
                                val commentItem = CommentItem(userId, comment)
                                commentItemList.add(commentItem)
                                commentAdapter.notifyDataSetChanged()

                            }
                            .addOnFailureListener {
                                // Handle the failure
                            }
                    }
                }
            }
        }
    }

    private fun getCommentsFromFirestore(): List<CommentItem> {

        val url = arguments?.getString("uid")
        val db = Firebase.firestore

        val postCollection = db.collection("photos")
        val commentItemList = mutableListOf<CommentItem>()

        postCollection.get().addOnSuccessListener { at ->
            at?.forEach { it ->
                val value = it.get("url") as String
                if (url == value) {
                    db.collection("photos/${it.reference.id}/comments").get().addOnSuccessListener {
                        it.documents.forEach { et->
                            val userId = et.getString("userUid")
                            val comment = et.getString("comment")

                            userId?.let {
                                val commentItem = CommentItem(userId, comment?: "")
                                commentItemList.add(commentItem)
                            }
                            commentAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        }
        return commentItemList
    }
}