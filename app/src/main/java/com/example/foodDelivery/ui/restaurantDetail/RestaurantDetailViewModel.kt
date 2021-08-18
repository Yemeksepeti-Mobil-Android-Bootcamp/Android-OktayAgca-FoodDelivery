package com.example.foodDelivery.ui.restaurantDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.foodDelivery.data.Repository
import com.example.foodDelivery.data.entity.restaurant.RestaurantResponse
import com.example.foodDelivery.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: Repository
):ViewModel() {

    fun getRestaurant(): LiveData<Resource<RestaurantResponse>> =
        savedStateHandle.get<String>("restaurantId")!!.let {
            repository.getRestaurantById(it)
        }
}