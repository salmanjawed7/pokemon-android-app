package com.salman.data.apiservice

import com.salman.data.models.detail.PokemonDetailApiModel
import com.salman.data.models.list.PokemonListApiModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {

  @GET("pokemon")
  suspend fun pokemonList(@Query("offset") offset: Int, @Query("limit") limit: Int): PokemonListApiModel

  @GET("pokemon/{name}")
  suspend fun pokemonDetail(@Path("name") name: String): PokemonDetailApiModel
}