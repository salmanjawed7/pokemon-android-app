package com.salman.pokemonapp.di

import android.app.Application
import com.salman.data.di.NetworkModule
import com.salman.pokemonapp.MainActivity
import com.salman.pokemonapp.PokemonApplication
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    NetworkModule::class,
    AppModule::class,
    ViewModelModule::class,
  ]
)
interface AppComponent {
  fun inject(mainActivity: MainActivity)
}