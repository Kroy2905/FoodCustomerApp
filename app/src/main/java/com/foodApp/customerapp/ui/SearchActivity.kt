package com.foodApp.customerapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import com.foodApp.customerapp.Utilities.OnItemClickListener
import com.foodApp.customerapp.Utilities.searchItemInterface
import com.foodApp.customerapp.adapters.SearchItemAdapter
import com.foodApp.customerapp.base.BaseActivity
import com.foodApp.customerapp.databinding.ActivityLoginBinding
import com.foodApp.customerapp.databinding.ActivitySearchBinding
import com.foodApp.customerapp.models.searchItems
import com.foodApp.customerapp.viewmodels.LoginViewModel
import com.foodApp.customerapp.viewmodels.SearchViewmodel

class SearchActivity :BaseActivity<ActivitySearchBinding, SearchViewmodel>(
    SearchViewmodel::class.java,
    { inflater -> ActivitySearchBinding.inflate(inflater) },
){

    private lateinit var itemAdapter: SearchItemAdapter


    override fun setupViews() {
        val myList = intent.getParcelableArrayListExtra<searchItems>("list")
        Log.d("LIst of searchItems",myList.toString())



        // Create and set up the adapter for your RecyclerView
        itemAdapter = SearchItemAdapter(myList!!)
        binding.matchitemsRecyclerview.adapter = itemAdapter


        // Set up item click listener
        itemAdapter.setOnItemClickListener(object : SearchItemAdapter.OnItemClickListener {
            override fun onItemClick(item: searchItems) {
                // Handle item click
                Toast.makeText(this@SearchActivity, "Clicked on ${item.name}", Toast.LENGTH_SHORT).show()
            }
        })




        // Use the received list in the activity


       binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // Handle the search query submission if needed
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // Filter the adapter with the entered search query
                itemAdapter.filter.filter(newText)
                return true
            }
        })
    }
    }


