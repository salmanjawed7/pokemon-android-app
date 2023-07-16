package com.salman.data.models.detail

import com.google.gson.annotations.SerializedName
import com.salman.data.models.detail.Type

data class Types(
  @SerializedName("slot") val slot: Int,
  @SerializedName("type") val type: Type,
)