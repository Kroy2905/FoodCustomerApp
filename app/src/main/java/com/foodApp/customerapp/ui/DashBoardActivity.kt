package com.foodApp.customerapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.foodApp.customerapp.R
import com.foodApp.customerapp.adapters.CategoryAdapter
import com.foodApp.customerapp.adapters.FastDeliveryAdapter
import com.foodApp.customerapp.base.BaseActivity
import com.foodApp.customerapp.databinding.ActivityDashBoardBinding
import com.foodApp.customerapp.models.CategoryDomain
import com.foodApp.customerapp.models.FastDeliveryDomain
import com.foodApp.customerapp.viewmodels.DashBoardviewModel

class DashBoardActivity : BaseActivity<ActivityDashBoardBinding, DashBoardviewModel>(
DashBoardviewModel::class.java,
{ inflater -> ActivityDashBoardBinding.inflate(inflater) }
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = mutableListOf<CategoryDomain>()
        data.add(CategoryDomain("Pizza", R.drawable.pizza))
        data.add(CategoryDomain("Burger", R.drawable.burger))
        data.add(CategoryDomain("Biryani", R.drawable.biriyani))
        data.add(CategoryDomain("Noodles", R.drawable.noodles))
        val adapter = CategoryAdapter(data)
        binding.recyclerview1.adapter = adapter


        val data2 = mutableListOf<FastDeliveryDomain>()
        data2.add(FastDeliveryDomain("Pizza Hut",R.drawable.pizzafullimg,"4.2 (30k+ reviews)",32))
        data2.add(FastDeliveryDomain("Dada Bowdi",R.drawable.biryanifullimg,"4.7 (10k+ reviews)",52))
        data2.add(FastDeliveryDomain("Chowman",R.drawable.noodlesfullimg,"4.0 (3000+ reviews)",22))
        data2.add(FastDeliveryDomain("Burger King",R.drawable.burgerfullimg,"4.2 (300+ reviews)",45))
        val adapter2 = FastDeliveryAdapter(data2)
        binding.recyclerview2.adapter = adapter2
    }
    override fun setupViews() {



    }

}