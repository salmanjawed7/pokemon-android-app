package com.salman.pokemonapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.salman.domain.repositories.PokemonRepository
import com.salman.pokemonapp.di.ViewModelFactory
import com.salman.pokemonapp.ui.detail.PokemonDetailScreen
import com.salman.pokemonapp.ui.list.PokemonListScreen
import com.salman.pokemonapp.ui.list.PokemonListViewModel
import com.salman.pokemonapp.ui.theme.PokemonAppTheme
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : ComponentActivity() {

  @Inject
  lateinit var pokemonRepository: PokemonRepository

  @Inject
  lateinit var viewModelFactory: ViewModelFactory


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    AndroidInjection.inject(this)
    setContent {
      PokemonAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          Inject(viewModelFactory) {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "list") {
              composable("list") {
                val viewModel: PokemonListViewModel = daggerViewModel()
                PokemonListScreen(viewModel)
              }
              composable("detail") { PokemonDetailScreen() }
            }
          }

        }
      }
    }
  }
}

@Composable
inline fun <reified VM : ViewModel> daggerViewModel(): VM {
  val factory = getViewModelFactory()
  return viewModel {
    factory.create(VM::class.java)
  }
}

@Composable
@PublishedApi
internal fun getViewModelFactory(): ViewModelFactory {
  return checkNotNull(LocalViewModelFactory.current) {
    "No ViewModelFactory was provided via LocalViewModelFactory"
  }
}

object LocalViewModelFactory {
  private val LocalViewModelFactory =
    compositionLocalOf<ViewModelFactory?> { null }

  val current: ViewModelFactory?
    @Composable
    get() = LocalViewModelFactory.current

  infix fun provides(viewModelFactory: ViewModelFactory):
      ProvidedValue<ViewModelFactory?> {
    return LocalViewModelFactory.provides(viewModelFactory)
  }
}

@Composable
fun Inject(
  viewModelFactory: ViewModelFactory,
  content: @Composable () -> Unit
) {
  CompositionLocalProvider(
    LocalViewModelFactory provides viewModelFactory,
    content = content
  )
}