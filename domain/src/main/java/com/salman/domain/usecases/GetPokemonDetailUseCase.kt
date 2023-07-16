package com.salman.domain.usecases

import com.salman.domain.models.PokemonDetailModel
import com.salman.domain.models.Resource
import com.salman.domain.repositories.PokemonRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetPokemonDetailUseCase @Inject constructor(
  private val pokemonRepository: PokemonRepository
) {
  suspend operator fun invoke(name: String): Flow<Resource<PokemonDetailModel>> {
    return pokemonRepository.getPokemonDetail(name)
  }
}