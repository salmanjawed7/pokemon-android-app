package com.salman.data.di

import com.salman.data.apiservice.PokemonApiService
import com.salman.data.repo.PokemonRepositoryImpl
import com.salman.domain.repositories.PokemonRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

  @Provides
  fun provideOkHttpClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
  }

  @Singleton
  @Provides
  fun providesRetroFit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create()).build()
  }

  @Singleton
  @Provides
  fun providesPokemonApiService(retrofit: Retrofit): PokemonApiService {
    return retrofit.create(PokemonApiService::class.java)
  }

  @Provides
  fun providesPokemonRepository(pokemonApiService: PokemonApiService): PokemonRepository {
    return PokemonRepositoryImpl(pokemonApiService)
  }
}