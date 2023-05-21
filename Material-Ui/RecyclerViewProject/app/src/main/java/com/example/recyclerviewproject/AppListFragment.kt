package com.example.recyclerviewproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recyclerviewproject.databinding.FragmentAppListBinding

class AppListFragment : Fragment() {

    private lateinit var binding: FragmentAppListBinding
    private lateinit var adapter: AppListRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAppListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AppListRecyclerAdapter(AppObject.appList)
        binding.recyclerView.layoutManager = GridLayoutManager(context,2)
        binding.recyclerView.adapter = adapter

        adapter.onClick = {
            val bundle = Bundle().apply {
                putParcelable("data",it)
            }
            findNavController().navigate(R.id.action_appListFragment_to_appInfoFragment,bundle)
        }
    }

}