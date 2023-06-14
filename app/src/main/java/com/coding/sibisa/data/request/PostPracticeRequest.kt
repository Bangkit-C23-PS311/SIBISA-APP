package com.coding.sibisa.data.request

import com.google.gson.annotations.SerializedName

data class PostPracticeRequest(

	@field:SerializedName("answer")
	val answer: Boolean? = null,

	@field:SerializedName("practice_id")
	val practiceId: Int? = null,

	@field:SerializedName("question_id")
	val questionId: Int? = null
)
