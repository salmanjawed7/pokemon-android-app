package com.salman.data.models.detail

import com.google.gson.annotations.SerializedName
import com.salman.data.models.detail.Move

data class Moves(
  @SerializedName("move") val move: Move,
)