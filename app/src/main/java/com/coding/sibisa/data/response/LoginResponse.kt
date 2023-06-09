package com.coding.sibisa.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class LoginResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
@Parcelize
data class User(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("token")
	val token: String? = null
):Parcelable
data class ParamLogin(
	@SerializedName("email")
	val email: String,
	@SerializedName("password")
	val password: String,
)

data class Data(

	@field:SerializedName("user")
	val user: User? = null
)


data class MyUser(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("isLogin")
	val isLogin: Boolean,

	@field:SerializedName("token")
	val token: String
)

