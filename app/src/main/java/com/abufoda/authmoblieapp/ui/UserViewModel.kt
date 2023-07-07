package com.abufoda.authmoblieapp.ui

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.abufoda.authmoblieapp.model.RegisterUser
import com.abufoda.authmoblieapp.model.UserInfo
import com.abufoda.authmoblieapp.model.remote.RemoteRepositoryImp
import com.abufoda.authmoblieapp.model.remote.RetrofitBuilder
import com.abufoda.authmoblieapp.model.remote.ServiceApi
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserViewModel(application: Application) : AndroidViewModel(application) {

    var userInfo: UserInfo
    private var remoteRepositoryImp: RemoteRepositoryImp


    private var addUserApiMutableLiveData = MutableLiveData<UserInfo>()
    val addUserApiLiveData : LiveData<UserInfo> get() = addUserApiMutableLiveData

    private var getUserApiMutableLiveData = MutableLiveData<UserInfo>()
    val getUserApiLiveData : LiveData<UserInfo> get() = getUserApiMutableLiveData

    private var getByIdMutableLiveData = MutableLiveData<UserInfo>()
    val getByIdLiveData : LiveData<UserInfo> get() = getByIdMutableLiveData

    init {
        var serviceInstance = RetrofitBuilder.getRetrofitBuilder().create(ServiceApi::class.java)
        remoteRepositoryImp = RemoteRepositoryImp(serviceInstance)
        userInfo = UserInfo(null,null,null,null,null,null,null,null)
    }

    fun getUserApi(userName : String)  = viewModelScope.launch {
        var result = remoteRepositoryImp.getUser(userName)
        if (result.isSuccessful){
            if (result.body() != null){
                getUserApiMutableLiveData.postValue(result.body())
            }
        }else{
            Log.i("TAG",result.message())
        }
    }


    fun addUserApi(registerUser: RegisterUser): UserInfo{

        var result = remoteRepositoryImp.addUser(registerUser)
        result.enqueue(object :Callback<UserInfo>{
            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
               try {
                   userInfo = response.body()!!
                   Log.i("TAG",userInfo.userName.toString())
               }catch(e: Exception){
                   Log.i("TAG",e.message.toString())
               }
            }

            override fun onFailure(call: Call<UserInfo>, t: Throwable) {

            }
        })
        return userInfo
    }

    fun getById(id : String)  = viewModelScope.launch {
        var result = remoteRepositoryImp.getById(id)
        if (result.isSuccessful){
            if (result.body() != null){
                getByIdMutableLiveData.postValue(result.body())
            }
        }else{
            Log.i("TAG",result.message())
        }
    }
}