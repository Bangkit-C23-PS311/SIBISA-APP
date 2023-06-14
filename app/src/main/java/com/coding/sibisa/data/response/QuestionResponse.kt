package com.coding.sibisa.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class QuestionResponse(

	@field:SerializedName("data")
	val data: List<List<DataItem>?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)


data class DataItem(

	@field:SerializedName("question")
	val question: String? = null,

	@field:SerializedName("answer")
	val answer: String? = null,

	@field:SerializedName("practice_id")
	val practiceId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
