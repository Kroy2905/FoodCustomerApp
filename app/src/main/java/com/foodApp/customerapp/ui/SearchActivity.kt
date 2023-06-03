package com.foodApp.customerapp.ui

import android.content.Intent
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
   private lateinit var category: String
    private lateinit var itemAdapter: SearchItemAdapter
    private  var  searchItemsList = mutableListOf<searchItems>()
    override fun onCreate(savedInstanceState: Bundle?) {
        val myList = intent.getParcelableArrayListExtra<searchItems>("list")
        Log.d("LIst of searchItems",myList.toString())
        // Use the received list in the activity
         category= intent.getStringExtra("category") !!

        //Adding all restaurants
        searchItemsList.clear()
        for(x in myList!!){
            searchItemsList.add(x)
        }
        viewModel.getallFoodItems()
        super.onCreate(savedInstanceState)
    }


    override fun setupViews() {

        viewModel.allFooditems.observe(this){
            for(x in it){
                searchItemsList.add(searchItems(name = x.foodTitle,
                    imageUrl = x.foodImgUrl,
                    type = 2,
                    id = x.restaurantID))
            }


            // Create and set up the adapter for your RecyclerView
            itemAdapter = SearchItemAdapter(searchItemsList)
            binding.matchitemsRecyclerview.adapter = itemAdapter
            // Set up item click listener
            itemAdapter.setOnItemClickListener(object : SearchItemAdapter.OnItemClickListener {
                override fun onItemClick(item: searchItems) {
                    // Handle item click

                    val intent = Intent(this@SearchActivity, RestaurantFoodItemActivity::class.java)
                    intent.putExtra("restaurantId", item.id)
                    startActivity(intent)
                   // Toast.makeText(this@SearchActivity, "Clicked on ${item.name}", Toast.LENGTH_SHORT).show()
                }
            })

                    // For category search
            if(category!=""){
                binding.searchView.setQuery(category,false)
                binding.searchView.clearFocus()
                // Filter the adapter with the entered search query
                itemAdapter.filter.filter(category)
            }
        }












       binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

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


