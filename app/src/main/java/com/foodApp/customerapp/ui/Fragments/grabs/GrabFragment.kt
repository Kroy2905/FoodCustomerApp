package com.foodApp.customerapp.ui.Fragments.grabs

import android.content.Intent
import com.foodApp.customerapp.Utilities.OnItemClickListener
import com.foodApp.customerapp.adapters.Restaurantitemadapter
import com.foodApp.customerapp.base.BaseFragment
import com.foodApp.customerapp.databinding.FragmentGrabBinding
import com.foodApp.customerapp.ui.RestaurantFoodItemActivity

class GrabFragment : BaseFragment<FragmentGrabBinding, GrabViewModel>(
GrabViewModel::class.java,
FragmentGrabBinding::inflate
) {


    override fun setupViews() {

        viewModel.getRestaurantItems.observe(this){
            binding.restaurantItemListRecyclerview.adapter=Restaurantitemadapter(it,object :OnItemClickListener{
                override fun onItemClick(position: Int) {
                    val intent = Intent(requireContext(), RestaurantFoodItemActivity::class.java)
                    intent.putExtra("restaurantId", it[position].restaurantID)
                    intent.putExtra("restaurantName", it[position].restaurantName)
                    intent.putExtra("restaurantAddress", it[position].restaurantAddress)
                    startActivity(intent)
              //  Toast.makeText(requireContext(),it[position].restaurantName,Toast.LENGTH_SHORT).show()
                }

            })
        }
        viewModel.getRestaurantItems()

    }

}