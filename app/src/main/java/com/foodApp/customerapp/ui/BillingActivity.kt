package com.foodApp.customerapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.foodApp.customerapp.Utilities.OnCartItemChangeListener
import com.foodApp.customerapp.adapters.cartItemAdapter
import com.foodApp.customerapp.adapters.fooditemadapter
import com.foodApp.customerapp.base.BaseActivity
import com.foodApp.customerapp.databinding.ActivityBillingBinding
import com.foodApp.customerapp.databinding.ActivityLoginBinding
import com.foodApp.customerapp.models.cartItems
import com.foodApp.customerapp.models.searchItems
import com.foodApp.customerapp.viewmodels.LoginViewModel
import java.util.*

class BillingActivity :   BaseActivity<ActivityBillingBinding, LoginViewModel>(
    LoginViewModel::class.java,
    { inflater -> ActivityBillingBinding.inflate(inflater) },
), OnCartItemChangeListener {
    var cartItemsList = mutableListOf<cartItems>()
    val discountprice = 34

    override fun setupViews() {
        val myList = intent.getParcelableArrayListExtra<cartItems>("cartlist")
        Log.d("cartList", myList.toString())
        val restauranName = intent.getStringExtra("restaurantName")


        for(x in myList!!){
            if(!cartItemsList.contains(x)){
                cartItemsList.add(x)
            }
        }

        val fooditemadaper = cartItemAdapter(myList, this@BillingActivity,this)
        //   binding.foodItemListRecyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.cartRecyclerView.adapter = fooditemadaper


        binding.actionBar.titleText.text=restauranName!!
        binding.cartTotalText.text=getTotalAmount().toString()
        binding.totalamount.text=(getTotalAmount()+discountprice).toString()
        binding.amountToPayText.text=(getTotalAmount()+discountprice).toString()
    }
    fun getTotalitemCount(): Int{
        var totalCartitems=0
        for(x in cartItemsList){
            totalCartitems+=x.quantity
        }
        return totalCartitems
    }

    fun getTotalAmount(): Int{
        var totalAmount=0
        for(x in cartItemsList){
            Log.d("amount calc->","quant ${x.quantity} , name ${x.foodTitle} , price ${x.price}")


            totalAmount+=(x.quantity*Integer.parseInt(x.price))
        }
        Log.d("amount calc->","--------------------------------------------")
        return totalAmount
    }


    override fun onCartItemChange(cartItem: cartItems) {
        Log.d("item cart",cartItemsList.size.toString())

        val index = cartItemsList.indexOf(cartItem)
        if (index != -1) {



            // If the cartItem already exists in the list, replace it with the received cartItem
            if(cartItemsList[index].quantity==0)
                cartItemsList.removeAt(index)
            else
                cartItemsList[index] = cartItem

            // binding.cartitemCount.text=totalCartitems.toString()


        } else {
            // If the cartItem doesn't exist in the list, add it
            cartItemsList.add(cartItem)

            //  binding.cartitemCount.text=totalCartitems.toString()
        }
        val totalitems = getTotalitemCount()

        if(totalitems<=0){
            val intent = Intent(this, LandingPageActivity::class.java)
            startActivity(intent)
        }else{
            binding.cartTotalText.text=getTotalAmount().toString()
            binding.totalamount.text=(getTotalAmount()-discountprice).toString()
            binding.amountToPayText.text=(getTotalAmount()-discountprice).toString()
        }


        val fooditemadaper = cartItemAdapter(cartItemsList, this@BillingActivity,this)
        //   binding.foodItemListRecyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.cartRecyclerView.adapter = fooditemadaper



    }

    }
