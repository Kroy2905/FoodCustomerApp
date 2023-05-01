package com.foodApp.customerapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.foodApp.customerapp.base.BaseActivity
import com.foodApp.customerapp.databinding.ActivityDashBoardBinding
import com.foodApp.customerapp.viewmodels.DashBoardviewModel

class DashBoardActivity : BaseActivity<ActivityDashBoardBinding, DashBoardviewModel>(
DashBoardviewModel::class.java,
{ inflater -> ActivityDashBoardBinding.inflate(inflater) }
) {
    override fun setupViews() {

    }

}