package com.example.searchbarproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchbarproject.databinding.FragmentSearchBinding
import java.util.*


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: SearchAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SearchAdapter(NameList.nameList)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        binding.apply {
            iV1.setOnClickListener {

            }
        }



        binding.apply {
            toggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
                if (isChecked) {
                    when(checkedId) {
                        R.id.btn_name -> {
                            searchByName()
                        }

                        R.id.btn_surname -> {
                            searchBySurname()
                        }
                    }
                }
            }
        }
    }

    private fun searchByName() {
        binding.searchView.setOnQueryTextListener(object: android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String): Boolean {
                val deger  =  NameList.nameList.filter { it.name.lowercase().contains(p0.lowercase()) }
                if (deger.isEmpty()){
                    adapter.data = NameList.nameList
                    adapter.notifyDataSetChanged()
                }else{
                    println(deger)
                    adapter.data = arrayListOf()
                    adapter.data = deger as ArrayList<SearchDataClass>
                    adapter.notifyDataSetChanged()
                }
                return true
            }

        })
    }

    private fun searchBySurname() {
        binding.searchView.setOnQueryTextListener(object: android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String): Boolean {

                val deger  =  NameList.nameList.filter { it.surName.lowercase().contains(p0.lowercase()) }
                if (deger.isEmpty()){
                    adapter.data = NameList.nameList
                    adapter.notifyDataSetChanged()
                }else{
                    adapter.data = deger as ArrayList<SearchDataClass>
                    adapter.notifyDataSetChanged()
                }
                return true
            }

        })
    }





}