package com.example.firebaseproject

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebaseproject.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.photoRecyclerView.layoutManager = LinearLayoutManager(activity)
        menuItems()

        auth = Firebase.auth
        val photoItemList = getPhotosFromFirestore()

        postAdapter = PostAdapter(photoItemList)
        binding.photoRecyclerView.adapter = postAdapter
    }

    private fun getPhotosFromFirestore(): List<PostItem> {
        val photosCollection = Firebase.firestore.collection("photos")

        val photoItemList = mutableListOf<PostItem>()

        photosCollection.get().addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot.documents) {
                val photoUrl = document.getString("url")
                val uploaderUid = document.getString("uploaderUid")

                photoUrl?.let {
                    val photoItem = PostItem(photoUrl, uploaderUid ?: "")
                    photoItemList.add(photoItem)
                }
            }

            postAdapter.notifyDataSetChanged()
        }
        return photoItemList
    }






    private fun menuItems() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.home_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.menuLogOut -> {
                        val intent = Intent(activity,MainActivity::class.java)
                        auth.signOut()
                        startActivity(intent)
                        true
                    }
                    R.id.uploadPhoto -> {
                        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToUploadPhotoFragment())
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

}