package com.example.foodDelivery.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.foodDelivery.R
import com.example.foodDelivery.databinding.FragmentOnboardingThirdScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingThirdScreenFragment:Fragment() {

    private lateinit var binding: FragmentOnboardingThirdScreenBinding
    private val viewModel: OnboardingViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingThirdScreenBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        initViews()
        return view
    }

    private fun initViews() {
        binding.homeButton.setOnClickListener{
            viewModel.saveOnboardingState("onboarding","true")
            findNavController().navigate(R.id.action_onboardingMainFragment_to_signUpFragment)
        }
    }
}