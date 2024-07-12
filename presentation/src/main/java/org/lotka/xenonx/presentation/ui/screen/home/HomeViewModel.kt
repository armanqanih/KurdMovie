package org.lotka.xenonx.presentation.ui.screen.home

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.lotka.xenonx.domain.usecase.GetMovieById
import org.lotka.xenonx.domain.usecase.GetMovies
import org.lotka.xenonx.domain.util.Resource
import org.lotka.xenonx.presentation.util.Category
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val getMovies: GetMovies,
): ViewModel() {

  private val _state = MutableStateFlow(MovieListState())
  val state = _state.asStateFlow()

  init {
    viewModelScope.launch {
    getPopularMovieList(false)
    getUpcomingMovieList(false)
    }
  }


  fun onEvent(event: MovieListUiEvent) {
    when (event) {
      is MovieListUiEvent.Paginate -> {
        if (event.category == Category.POPULAR) {
          viewModelScope.launch {
          getPopularMovieList(true)

          }
        } else if (event.category == Category.UPCOMING) {
          viewModelScope.launch {
          getUpcomingMovieList(true)
          }
        }
      }

      MovieListUiEvent.Navigate -> {
        _state.update {
          it.copy(isCurrentPopularScreen = !state.value.isCurrentPopularScreen) }
      }
    }


  }


  private suspend fun getUpcomingMovieList(forceFetchFromRemote: Boolean) {
    getMovies.invoke(
      forceFetchFromRemote,
      Category.UPCOMING,
      state.value.upcomingMovieListPage
    ).collectLatest { result ->
      when (result) {
        is Resource.Error -> {
          _state.update {
            it.copy(isLoading = false)
          }
        }

        is Resource.Loading -> {
          _state.update {
            it.copy(isLoading = result.isLoading)
          }
        }

        is Resource.Success -> {
          result.data?.let { upcomingMovie ->
            _state.update {
              it.copy(
                upcomingMovieList = it.upcomingMovieList + upcomingMovie,
                popularMovieListPage = it.upcomingMovieListPage + 1
              )
            }
          }
        }

      }
    }

  }


  private suspend fun getPopularMovieList(forceFetchFromRemote: Boolean) {
    getMovies.invoke(
      forceFetchFromRemote,
      Category.POPULAR,
      state.value.popularMovieListPage
    ).collectLatest { result ->
      when (result) {
        is Resource.Error -> {
          _state.update {
            it.copy(isLoading = false)
          }
        }

        is Resource.Loading -> {
          _state.update {
            it.copy(isLoading = result.isLoading)
          }
        }

        is Resource.Success -> {
          result.data?.let { popularMovie ->
            _state.update {
              it.copy(
                upcomingMovieList = it.popularMovieList + popularMovie,
                popularMovieListPage = it.popularMovieListPage + 1
              )
            }
          }
        }

      }
    }

  }

}