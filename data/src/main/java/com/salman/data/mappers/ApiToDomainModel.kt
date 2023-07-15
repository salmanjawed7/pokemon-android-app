package com.salman.data.mappers

import com.salman.data.models.detail.PokemonDetailApiModel
import com.salman.data.models.list.Pokemon
import com.salman.domain.models.PokemonDetailModel
import com.salman.domain.models.PokemonListModel
import com.salman.domain.models.PokemonStatModel

fun Pokemon.toPokemonListModel(): PokemonListModel {
  return PokemonListModel(
    name = name,
    url = url
  )
}

fun PokemonDetailApiModel.toPokemonDetailModel(): PokemonDetailModel {
  return PokemonDetailModel(
    id = id.toString(),
    name = name,
    height = height,
    weight = weight,
    imageUrl = sprites.frontDefault,
    movesList = moves.map { it.move.name },
    stats = stats.map { PokemonStatModel(it.baseStat, it.stat.name) },
    type = types.map { it.type.name }
  )
}

