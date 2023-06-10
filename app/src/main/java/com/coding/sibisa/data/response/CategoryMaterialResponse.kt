package com.coding.sibisa.data.response

import com.google.gson.annotations.SerializedName

data class CategoryMaterialResponse(

	@field:SerializedName("data")
	val data: List<List<DataItemItem?>?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataItemItem(

	@field:SerializedName("category_id")
	val categoryId: Int? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("class_model")
	val classModel: String? = null
)
