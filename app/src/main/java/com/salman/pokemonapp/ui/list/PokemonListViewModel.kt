package com.salman.pokemonapp.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salman.domain.models.PokemonListModel
import com.salman.domain.models.Resource
import com.salman.domain.usecases.GetPokemonListUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

const val LIMIT = 20

class PokemonListViewModel @Inject constructor(
  private val pokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {

  private var offset = 0

  private val _viewState =
    MutableStateFlow(PokemonListViewState(isLoading = true))
  val viewState: StateFlow<PokemonListViewState> = _viewState

  fun loadPokemonList() {
    viewModelScope.launch {
      pokemonListUseCase.invoke(offset, LIMIT).collect { response ->
        when (response) {
          is Resource.DataError -> _viewState.value =
            _viewState.value.copy(isLoading = false, isError = true)

          is Resource.Loading -> _viewState.value =
            _viewState.value.copy(isLoading = true, isError = false)

          is Resource.Success -> {
            val pokemon = if (_viewState.value.pokemon.isNotEmpty()) {
              _viewState.value.pokemon + response.data!!
            } else {
              response.data!!
            }
            _viewState.value =
              _viewState.value.copy(isLoading = false, isError = false, pokemon = pokemon)
            offset += LIMIT
          }
        }
      }
    }
  }

  init {
    loadPokemonList()
  }
}

data class PokemonListViewState(
  val isLoading: Boolean = false,
  val isError: Boolean = false,
  val pokemon: List<PokemonListModel> = listOf()
)