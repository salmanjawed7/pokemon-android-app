package com.salman.data.models.list

import com.google.gson.annotations.SerializedName

data class PokemonListApiModel(
  @SerializedName("count") val count: Int,
  @SerializedName("next") val next: String? = null,
  @SerializedName("previous") val previous: String? = null,
  @SerializedName("results") val pokemon: ArrayList<Pokemon> = arrayListOf()
)