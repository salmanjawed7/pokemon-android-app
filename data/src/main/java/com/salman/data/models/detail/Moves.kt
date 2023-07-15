package com.salman.data.models.detail

import com.google.gson.annotations.SerializedName

data class Moves(
  @SerializedName("move") val move: Move,
)