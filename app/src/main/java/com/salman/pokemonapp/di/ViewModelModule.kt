package com.salman.pokemonapp.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
internal abstract class ViewModelModule {

  @Binds
  internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


  /*
   * This method basically says
   * inject this object into a Map using the @IntoMap annotation,
   * with the  MovieListViewModel.class as key,
   * and a Provider that will build a MovieListViewModel
   * object.
   *
   * */

//  @Binds
//  @IntoMap
//  @ViewModelKey(MovieListViewModel::class)
//  protected abstract fun movieListViewModel(moviesListViewModel: MovieListViewModel): ViewModel
}