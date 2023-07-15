package com.salman.data.repo

import com.salman.data.apiservice.PokemonApiService
import com.salman.data.mappers.toPokemonDetailModel
import com.salman.data.mappers.toPokemonListModel
import com.salman.domain.models.PokemonDetailModel
import com.salman.domain.models.PokemonListModel
import com.salman.domain.repositories.PokemonRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PokemonRepositoryImpl @Inject constructor(
  private val pokemonApiService: PokemonApiService
) : PokemonRepository {

  override suspend fun getPokemonList(offset: Int, limit: Int): Flow<PokemonListModel> =
    flow<PokemonListModel> {
//      pokemonApiService.pokemonList(offset, limit).
//      val test = pokemonApiService.pokemonList(offset, limit)
//      println("CHALAAA $test")
//      emit(pokemonApiService.pokemonList(offset, limit).pokemon.map { it.toPokemonListModel() })
    }.flowOn(Dispatchers.IO)

  override suspend fun getPokemonDetail(name: String): PokemonDetailModel {
    return pokemonApiService.pokemonDetail(name).toPokemonDetailModel()
  }
}