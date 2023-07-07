package com.abufoda.authmoblieapp.model

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("id") val id: String?,
    @SerializedName("user_name") val userName: String?,
    @SerializedName("user_email") val userEmail: String?,
    @SerializedName("firstName") val firstName: String?,
    @SerializedName("lastName") val lastName: String?,
    @SerializedName("gender") val gender: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("token") val token: String?)


data class RegisterUser(
    @SerializedName("user_name") val userName: String?,
    @SerializedName("password") val password: String?,
)
