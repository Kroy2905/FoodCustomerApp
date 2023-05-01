package com.foodApp.customerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.foodApp.customerapp.base.BaseActivity
import com.foodApp.customerapp.databinding.ActivityMainBinding
import com.foodApp.customerapp.viewmodels.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    MainViewModel::class.java,
    { inflater -> ActivityMainBinding.inflate(inflater) }
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.demoData.observe(this) {
            binding.textview.text=it.toString()
        }


    }

    override fun setupViews() {

        // Initialize any views here
    }
}