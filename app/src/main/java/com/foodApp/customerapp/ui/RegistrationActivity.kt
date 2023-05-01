package com.foodApp.customerapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.foodApp.customerapp.base.BaseActivity
import com.foodApp.customerapp.databinding.ActivityRegistrationBinding
import com.foodApp.customerapp.viewmodels.RegistrationViewModel

class RegistrationActivity : BaseActivity<ActivityRegistrationBinding, RegistrationViewModel>(
RegistrationViewModel::class.java,
{ inflater -> ActivityRegistrationBinding.inflate(inflater) }
) {
    override fun setupViews() {
        binding.progressLayout.visibility= View.INVISIBLE
        binding.contentRegistration.btnRegister.setOnClickListener {

        }
    }

}