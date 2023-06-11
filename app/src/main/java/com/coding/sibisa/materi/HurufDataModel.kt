package com.coding.sibisa.materi

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HurufDataModel(var title: String, var image: String?): Parcelable
