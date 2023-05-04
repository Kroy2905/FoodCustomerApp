package com.foodApp.customerapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.foodApp.customerapp.Utilities.Utils.Companion.SUCCESS
import com.foodApp.customerapp.base.BaseActivity
import com.foodApp.customerapp.databinding.ActivityRegistrationBinding
import com.foodApp.customerapp.models.custRegBody
import com.foodApp.customerapp.viewmodels.RegistrationViewModel


class RegistrationActivity : BaseActivity<ActivityRegistrationBinding, RegistrationViewModel>(
RegistrationViewModel::class.java,
{ inflater -> ActivityRegistrationBinding.inflate(inflater) }
) {




    var edittextList = mutableListOf<EditText>()
    override fun setupViews() {
        binding.progressLayout.visibility= View.INVISIBLE
        viewModel.registrationVar.observe(this){
            binding.progressLayout.visibility=View.INVISIBLE
            if(it.status == SUCCESS){
                val intent = Intent(this, DashBoardActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        binding.contentRegistration.btnRegister.setOnClickListener {
            edittextList.add(binding.contentRegistration.etName)
            edittextList.add(binding.contentRegistration.etEmail)
            edittextList.add(binding.contentRegistration.etMobileNumber)
            edittextList.add(binding.contentRegistration.etPassword)
            edittextList.add(binding.contentRegistration.etConfirmPassword)
            edittextList.add(binding.contentRegistration.etDeliveryAddress)
            if(!viewModel.checkEditTextEmpty(edittextList)){
                edittextList.clear()
                //If password and cnf password is matched
                if(binding.contentRegistration.etPassword.text.toString()
                        .equals(binding.contentRegistration.etConfirmPassword.text.toString())){

                    viewModel.registerCustomer(custRegBody(
                        binding.contentRegistration.etEmail.text.toString(),
                        binding.contentRegistration.etMobileNumber.text.toString(),
                        binding.contentRegistration.etName.text.toString(),
                        binding.contentRegistration.etPassword.text.toString()
                    ))
                    binding.progressLayout.visibility=View.VISIBLE

                }else{
                    Toast.makeText(this,"Password Mismatch !!",Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

}