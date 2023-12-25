package com.capstone.suitmediaassesment.api

import com.capstone.suitmediaassesment.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiInterface {

    @GET("users")
    fun getUsers(
        @QueryMap parameters: HashMap<String, String>
    ): Call<UserResponse>

}