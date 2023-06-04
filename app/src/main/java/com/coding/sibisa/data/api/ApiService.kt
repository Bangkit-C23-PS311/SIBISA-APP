package com.coding.sibisa.data.api

import com.coding.sibisa.data.response.LoginResponse
import com.coding.sibisa.data.response.ParamLogin
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun login(
        @Body req: ParamLogin
    ): LoginResponse
}