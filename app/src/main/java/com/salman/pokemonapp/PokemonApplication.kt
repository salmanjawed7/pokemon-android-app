package com.salman.pokemonapp

import android.content.Context
import androidx.multidex.MultiDex
import com.salman.pokemonapp.di.AppComponent
import com.salman.pokemonapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class PokemonApplication : DaggerApplication(), HasAndroidInjector {

  lateinit var appComponent: AppComponent

  @Inject
  lateinit var androidInjector : DispatchingAndroidInjector<Any>

  override fun attachBaseContext(base: Context) {
    super.attachBaseContext(base)
    MultiDex.install(this)
  }


  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    appComponent = DaggerAppComponent.builder()
      .application(this)
      .build()
    return appComponent
  }

  override fun androidInjector(): AndroidInjector<Any> = androidInjector



}