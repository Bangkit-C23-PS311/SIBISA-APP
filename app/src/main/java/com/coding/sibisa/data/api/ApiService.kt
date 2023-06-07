package com.coding.sibisa.data.api

import com.coding.sibisa.data.response.LoginResponse
import com.coding.sibisa.data.response.ParamLogin
import com.coding.sibisa.data.response.ParamRegister
import com.coding.sibisa.data.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers("Accept: application/json", "Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @POST("auth/register")
    suspend fun register(
        @Body req: ParamRegister
    ):RegisterResponse
}