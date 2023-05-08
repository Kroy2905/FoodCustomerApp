package com.foodApp.customerapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.foodApp.customerapp.base.BaseActivity
import com.foodApp.customerapp.databinding.ActivitySearchBinding
import com.foodApp.customerapp.databinding.ActivitySplashScreenBinding
import com.foodApp.customerapp.viewmodels.MainViewModel
import com.foodApp.customerapp.viewmodels.SearchActivityViewModel

class SearchActivity : BaseActivity<ActivitySearchBinding, SearchActivityViewModel>(
   SearchActivityViewModel::class.java,
    { inflater -> ActivitySearchBinding.inflate(inflater) }
) {

    override fun setupViews() {

    }
}