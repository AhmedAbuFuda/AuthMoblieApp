package com.abufoda.authmoblieapp.model.remote

import com.abufoda.authmoblieapp.model.RegisterUser
import com.abufoda.authmoblieapp.model.UserInfo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Query

interface RemoteRepository {

    fun addUser(registerUser: RegisterUser): Call<UserInfo>

    suspend fun getUser(userName: String): Response<UserInfo>

    suspend fun getById(@Query("id") id: String): Response<UserInfo>
}