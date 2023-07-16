package com.salman.pokemonapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.salman.pokemonapp.ui.list.PokemonListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

  @Binds
  internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


  @Binds
  @IntoMap
  @ViewModelKey(PokemonListViewModel::class)
  abstract fun bindPokemonListViewModel(viewModel: PokemonListViewModel): ViewModel
}