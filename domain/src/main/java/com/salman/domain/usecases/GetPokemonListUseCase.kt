package com.salman.domain.usecases

import com.salman.domain.models.PokemonListModel

interface GetPokemonListUseCase {
  suspend operator fun invoke(): Result<List<PokemonListModel>>
}