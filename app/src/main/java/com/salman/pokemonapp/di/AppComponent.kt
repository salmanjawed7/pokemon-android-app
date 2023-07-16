package com.salman.pokemonapp.di

import android.app.Application
import com.salman.data.di.NetworkModule
import com.salman.pokemonapp.PokemonApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    NetworkModule::class,
    AppModule::class,
    ViewModelModule::class,
    ActivityBindingModule::class,
    AndroidSupportInjectionModule::class,
    AndroidInjectionModule::class,
  ]
)
interface AppComponent : AndroidInjector<PokemonApplication> {
  @Component.Builder
  interface Builder {

    @BindsInstance
    fun application(application: Application): Builder

    fun build(): AppComponent
  }
}