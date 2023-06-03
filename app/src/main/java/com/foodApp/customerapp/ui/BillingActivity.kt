package com.foodApp.customerapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.foodApp.customerapp.base.BaseActivity
import com.foodApp.customerapp.databinding.ActivityBillingBinding
import com.foodApp.customerapp.databinding.ActivityLoginBinding
import com.foodApp.customerapp.models.searchItems
import com.foodApp.customerapp.viewmodels.LoginViewModel
import java.util.*

class BillingActivity :   BaseActivity<ActivityBillingBinding, LoginViewModel>(
    LoginViewModel::class.java,
    { inflater -> ActivityBillingBinding.inflate(inflater) },
)  {

    override fun setupViews() {
        val myList = intent.getParcelableArrayListExtra<searchItems>("cartlist")
        Log.d("cartList", myList.toString())
    }
}