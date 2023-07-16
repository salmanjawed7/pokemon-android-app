package com.salman.domain.usecases

import com.salman.domain.models.PokemonListModel
import com.salman.domain.models.Resource
import com.salman.domain.repositories.PokemonRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetPokemonListUseCase @Inject constructor(
  private val pokemonRepository: PokemonRepository
) {
  suspend operator fun invoke(offset: Int, limit: Int): Flow<Resource<List<PokemonListModel>>> {
    return pokemonRepository.getPokemonList(offset, limit)
  }
}