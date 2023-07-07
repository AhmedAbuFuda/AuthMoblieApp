package com.abufoda.authmoblieapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.abufoda.authmoblieapp.R
import com.abufoda.authmoblieapp.databinding.FragmentRegisterScreenBinding
import com.abufoda.authmoblieapp.model.RegisterUser
import com.abufoda.authmoblieapp.model.UserInfo


class RegisterScreen : Fragment() {

    private lateinit var binding : FragmentRegisterScreenBinding
    lateinit var viewModel: UserViewModel
    lateinit var userInfo: UserInfo
    lateinit var id : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        binding = DataBindingUtil.inflate<FragmentRegisterScreenBinding>(inflater,
            R.layout.fragment_register_screen, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerBtn.setOnClickListener{
            addUser(RegisterUser(binding.userName.toString(),binding.userPassword.toString()))
            val bundle = Bundle()
            bundle.putString("id", id)
            findNavController().navigate(R.id.action_logInScreen_to_profileScreen, bundle)
        }
        binding.logIn.setOnClickListener {
            findNavController().navigate(R.id.action_registerScreen_to_logInScreen)
        }
    }


    private fun addUser(registerUser: RegisterUser){
        userInfo = viewModel.addUserApi(registerUser)
        id = userInfo.id.toString()
    }




}