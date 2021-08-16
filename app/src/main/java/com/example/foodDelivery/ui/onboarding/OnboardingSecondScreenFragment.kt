package com.example.foodDelivery.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.foodDelivery.R
import com.example.foodDelivery.databinding.FragmentOnboardingSecondScreenBinding

class OnboardingSecondScreenFragment:Fragment() {
    private  lateinit var binding:FragmentOnboardingSecondScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingSecondScreenBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        initViews()
        return view
    }

    private fun initViews() {
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        binding.nextButton.setOnClickListener(){
            viewPager?.currentItem=2
        }
    }
}