package com.salman.domain.repositories

import com.salman.domain.models.PokemonDetailModel
import com.salman.domain.models.PokemonListModel
import com.salman.domain.models.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
  suspend fun getPokemonList(offset: Int, limit: Int): Flow<Resource<List<PokemonListModel>>>

  suspend fun getPokemonDetail(name: String): Flow<Resource<PokemonDetailModel>>
}