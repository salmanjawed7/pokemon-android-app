package com.salman.data.repo

import com.salman.data.apiservice.PokemonApiService
import com.salman.data.mappers.toPokemonDetailModel
import com.salman.data.mappers.toPokemonListModel
import com.salman.domain.models.PokemonDetailModel
import com.salman.domain.models.PokemonListModel
import com.salman.domain.models.Resource
import com.salman.domain.repositories.PokemonRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokemonRepositoryImpl @Inject constructor(
  private val pokemonApiService: PokemonApiService
) : PokemonRepository {
  override suspend fun getPokemonList(offset: Int, limit: Int): Flow<Resource<List<PokemonListModel>>> =
    flow {
//      emit(Resource.Loading(null))
      val response = pokemonApiService.pokemonList(offset, limit)
      if (response.isSuccessful && response.body() != null) {
        emit(Resource.Success(response.body()!!.pokemon.map { it.toPokemonListModel() }))
      } else {
        emit(Resource.DataError(100))
      }
    }

  override suspend fun getPokemonDetail(name: String): Flow<Resource<PokemonDetailModel>> = flow {
    emit(Resource.Loading(null))
    val response = pokemonApiService.pokemonDetail(name)
    if (response.isSuccessful && response.body() != null) {
      emit(Resource.Success(response.body()!!.toPokemonDetailModel()))
    } else {
      emit(Resource.DataError(100))
    }
  }
}