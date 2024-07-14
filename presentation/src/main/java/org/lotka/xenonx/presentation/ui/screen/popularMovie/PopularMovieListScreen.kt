package org.lotka.xenonx.presentation.ui.screen.popularMovie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import org.lotka.xenonx.presentation.ui.screen.home.HomeViewModel
import org.lotka.xenonx.presentation.ui.screen.home.MovieListState
import org.lotka.xenonx.presentation.ui.screen.home.MovieListUiEvent


@Composable
fun PopularMovieListScreen(
    navController: NavHostController,
    onEvent: (MovieListUiEvent) -> Unit,
    state: MovieListState
) {
    if (state.popularMovieList.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp)
        ) {

            items(state.popularMovieList.size) { index ->
                PopularMovieItem(
                    movie = state.popularMovieList[index],
                   navController = navController
                )
            }
        }



        }

}