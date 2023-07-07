package com.abufoda.authmoblieapp.model.remote

import com.abufoda.authmoblieapp.model.RegisterUser
import com.abufoda.authmoblieapp.model.UserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response

class RemoteRepositoryImp(private val api: ServiceApi) : RemoteRepository {

    override fun addUser(registerUser: RegisterUser) =
        api.addUser(registerUser)


    override suspend fun getUser(userName: String) =
        withContext(Dispatchers.IO){
            api.getUser(userName)
        }

    override suspend fun getById(id: String) =
        withContext(Dispatchers.IO){
            api.getById(id)
        }
}