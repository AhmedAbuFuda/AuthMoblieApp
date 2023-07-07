package com.abufoda.authmoblieapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.abufoda.authmoblieapp.R
import com.abufoda.authmoblieapp.databinding.FragmentProfileScreenBinding
import com.bumptech.glide.Glide


class ProfileScreen : Fragment() {

    private lateinit var binding: FragmentProfileScreenBinding
    private lateinit var viewModel: UserViewModel
    lateinit var id : String



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentProfileScreenBinding>(inflater,
            R.layout.fragment_profile_screen, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        id = arguments?.getString("id").toString()
        getById(id)

        binding.logOut.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_profileScreen_to_logInScreen))
    }

    private fun getById(id: String){
        viewModel.getById(id)
        viewModel.getUserApiLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null){
                binding.fullName.text = " ${it.firstName.toString()}"+" "+"${it.lastName.toString()}"
                binding.userName.text = it.userName.toString()
                binding.email.text = it.userEmail.toString()
                binding.gander.text = it.gender.toString()
                Glide.with(requireActivity())
                    .load(it.image.toString())
                    .into(binding.iconImage)
            }
        })
    }


}