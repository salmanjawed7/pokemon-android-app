package com.salman.domain.usecases

import com.salman.domain.models.PokemonDetailModel

interface GetPokemonDetailUseCase {
  suspend operator fun invoke(): Result<PokemonDetailModel>
}