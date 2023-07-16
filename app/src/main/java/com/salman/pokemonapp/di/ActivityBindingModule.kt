package com.salman.pokemonapp.di

import com.salman.pokemonapp.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
abstract class ActivityBindingModule {

  @ContributesAndroidInjector
  abstract fun bindMainScreenActivity(): MainActivity

}