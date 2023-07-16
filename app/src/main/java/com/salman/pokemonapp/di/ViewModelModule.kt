package com.salman.pokemonapp.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
internal abstract class ViewModelModule {

  @Binds
  internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


//  @Binds
//  @IntoMap
//  @ViewModelKey(MainViewModel::class)
//  abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}