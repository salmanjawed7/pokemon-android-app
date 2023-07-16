package com.salman.domain.repositories

import com.salman.domain.models.PokemonDetailModel
import com.salman.domain.models.PokemonListModel
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
  suspend fun getPokemonList(offset: Int, limit: Int): List<PokemonListModel>

  suspend fun getPokemonDetail(name: String): PokemonDetailModel
}