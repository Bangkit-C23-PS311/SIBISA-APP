package com.coding.sibisa.data.api

import com.coding.sibisa.data.request.PostMateriRequest
import com.coding.sibisa.data.request.PostPracticeRequest
import com.coding.sibisa.data.response.*
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

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

    @GET("category/{id}/material")
    suspend fun material(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): MaterialResponse

    @GET("practice/{id}/question")
    suspend fun question(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): QuestionResponse

    @POST("progress")
    suspend fun postDataProgressMaterial(
        @Header("Authorization") token: String,
        @Body requestBody: PostMateriRequest
    ) : Response<ResponseBody>

    @POST("practice")
    suspend fun postDataProgressPractice(
        @Header("Authorization") token: String,
        @Body requestBody: PostPracticeRequest
    ) : Response<ResponseBody>
}