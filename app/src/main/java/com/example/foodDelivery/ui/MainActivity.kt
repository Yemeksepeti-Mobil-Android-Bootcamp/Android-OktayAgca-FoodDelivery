package com.example.foodDelivery.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodDelivery.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  var  _binding:ActivityMainBinding? = null
    private  val binding get() = _binding !!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}