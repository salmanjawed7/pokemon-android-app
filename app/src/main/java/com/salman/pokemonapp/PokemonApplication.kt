package com.salman.pokemonapp

import android.app.Application
import com.salman.pokemonapp.di.AppComponent
import com.salman.pokemonapp.di.DaggerAppComponent

class PokemonApplication : Application() {

  lateinit var applicationComponent: AppComponent


  override fun onCreate() {
    super.onCreate()
    applicationComponent = DaggerAppComponent.builder().build()
  }

}