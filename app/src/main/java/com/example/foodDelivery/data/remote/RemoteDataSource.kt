package com.example.foodDelivery.data.remote

import com.example.foodDelivery.data.entity.login.LoginRequest
import com.example.foodDelivery.data.entity.register.RegisterRequest
import com.example.foodDelivery.utils.BaseDataSource
import javax.inject.Inject


class RemoteDataSource @Inject constructor(
    private val apiService: NetworkApiService
):BaseDataSource() {

    suspend fun  postRegister(request: RegisterRequest) =  getResult { apiService.register(request) }

    suspend fun  postLogin(request: LoginRequest) = getResult { apiService.login(request) }
}