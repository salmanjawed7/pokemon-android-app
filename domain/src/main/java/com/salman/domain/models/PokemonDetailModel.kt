package com.salman.domain.models

data class PokemonDetailModel(
  val id: String,
  val name: String,
  val weight: Int,
  val height: Int,
  val imageUrl: String,
  val type: List<String>,
  val movesList: List<String>,
  val stats: List<PokemonStatModel>,
)