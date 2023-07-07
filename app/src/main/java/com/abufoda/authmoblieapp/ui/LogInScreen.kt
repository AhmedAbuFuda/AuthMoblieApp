package com.abufoda.authmoblieapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.abufoda.authmoblieapp.R
import com.abufoda.authmoblieapp.databinding.FragmentLogInScreenBinding


class LogInScreen : Fragment() {

    private lateinit var binding : FragmentLogInScreenBinding
    private lateinit var viewModel: UserViewModel
    var id : String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)

         binding = DataBindingUtil.inflate<FragmentLogInScreenBinding>(inflater,
             R.layout.fragment_log_in_screen, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logInBtn.setOnClickListener {
            getUser(binding.userName.text.toString())
            val bundle = Bundle()
            bundle.putString("id", id)
            findNavController().navigate(R.id.action_logInScreen_to_profileScreen, bundle)
        }
        binding.register.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_logInScreen_to_registerScreen)
        )
    }

    private fun getUser(userName: String){
        viewModel.getUserApi(userName)
        viewModel.getUserApiLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null){
                id = it.id.toString()
            }
        })
    }


}