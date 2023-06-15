package com.coding.sibisa.data.response

import com.google.gson.annotations.SerializedName

data class ProgressResponse(

	@field:SerializedName("latihan")
	val latihan: Latihan? = null,

	@field:SerializedName("materi")
	val materi: Materi? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class Latihan(

	@field:SerializedName("1")
	val jsonMember1: String? = null,

	@field:SerializedName("2")
	val jsonMember2: String? = null
)

data class Materi(

	@field:SerializedName("1")
	val jsonMember1: String? = null,

	@field:SerializedName("2")
	val jsonMember2: String? = null
)
