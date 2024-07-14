package org.lotka.xenonx.presentation.ui.screen.home

import org.lotka.xenonx.domain.model.MovieModel


/**
 * @author Android Devs Academy (arman sherwanii)
 */

data class MovieListState(
    val isLoading: Boolean = false,

    val popularMovieListPage: Int = 1,
    val upcomingMovieListPage: Int = 1,

    val isCurrentPopularScreen: Boolean = true,

    val popularMovieList: List<MovieModel> = emptyList(),
    val upcomingMovieList: List<MovieModel> = emptyList()
)