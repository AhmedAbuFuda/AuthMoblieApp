package com.abufoda.authmoblieapp.model.remote

import com.abufoda.authmoblieapp.model.RegisterUser
import com.abufoda.authmoblieapp.model.UserInfo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ServiceApi {

    @Headers("Content-Type: application/json")
    @POST("/auth/login")
    fun addUser(@Body registerUser: RegisterUser): Call<UserInfo>


    @Headers("Content-Type: application/json")
    @GET("/auth/login/")
    suspend fun getUser(@Query ("username") userName: String): Response<UserInfo>

    @Headers("Content-Type: application/json")
    @GET("/auth/login/")
    suspend fun getById(@Query ("id") id: String): Response<UserInfo>

}