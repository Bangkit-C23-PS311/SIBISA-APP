package com.coding.sibisa.data.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("data")
	val data: DataRegister? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataRegister(

	@field:SerializedName("user")
	val user: Any? = null,

	@field:SerializedName("token")
	val token: String? = null
)


data class ParamRegister(
	@SerializedName("name")
	val name: String,
	@SerializedName("email")
	val email: String,
	@SerializedName("password")
	val password: String,
)

