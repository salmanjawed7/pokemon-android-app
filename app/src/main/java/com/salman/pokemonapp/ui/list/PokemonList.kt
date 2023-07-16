package com.salman.pokemonapp.ui.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun PokemonListScreen(
  viewModel: PokemonListViewModel
) {
  PokemonList(
    viewModel.viewState.collectAsState().value,
    viewModel::loadPokemonList
  )
}


@Composable
fun PokemonList(
  state: PokemonListViewState,
  loadMorePokemon: () -> Unit,
) {
  when {
    state.isLoading -> {
      Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
      }
    }

    state.isError -> {
      Box(modifier = Modifier.fillMaxSize()) {
        Text(modifier = Modifier.align(Alignment.Center), text = "Error Occurred")
      }
    }

    else -> {
      val lazyListState = rememberLazyListState()
      LazyColumn(state = lazyListState) {
        items(state.pokemon) { pokemon ->
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp)
          ) {
            AsyncImage(
              modifier = Modifier.size(75.dp),
              model = pokemon.imageUrl,
              contentDescription = "Image for ${pokemon.name}"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(modifier = Modifier.align(CenterVertically), text = pokemon.name)
          }
        }
      }

      lazyListState.OnBottomReached {
        loadMorePokemon()
      }
    }
  }
}

@Composable
fun LazyListState.OnBottomReached(
  loadMore: () -> Unit
) {
  val shouldLoadMore = remember {
    derivedStateOf {
      val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
        ?: return@derivedStateOf true

      lastVisibleItem.index == layoutInfo.totalItemsCount - 1
    }
  }

  // Convert the state into a cold flow and collect
  LaunchedEffect(shouldLoadMore) {
    snapshotFlow { shouldLoadMore.value }
      .collect {
        // if should load more, then invoke loadMore
        if (it) loadMore()
      }
  }
}