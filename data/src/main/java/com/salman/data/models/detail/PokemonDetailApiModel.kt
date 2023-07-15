package com.salman.data.models.detail

import com.google.gson.annotations.SerializedName

data class PokemonDetailApiModel(
  @SerializedName("id") val id: Int,
  @SerializedName("name") val name: String,
  @SerializedName("height") val height: Int,
  @SerializedName("weight") val weight: Int,
  @SerializedName("types") val types: ArrayList<Types> = arrayListOf(),
  @SerializedName("sprites") val sprites: Sprites,
  @SerializedName("moves") val moves: ArrayList<Moves> = arrayListOf(),
  @SerializedName("stats") val stats: ArrayList<Stats> = arrayListOf(),
)