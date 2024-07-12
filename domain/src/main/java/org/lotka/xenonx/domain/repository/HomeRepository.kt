package org.lotka.xenonx.domain.repository

import kotlinx.coroutines.flow.Flow
import org.lotka.xenonx.domain.model.MovieListModel
import org.lotka.xenonx.domain.model.MovieModel
import org.lotka.xenonx.domain.util.Resource


interface HomeRepository {

    suspend fun getMovies(fromFetchRemote: Boolean,category: String, page: Int): Flow<Resource<List<MovieModel>>>

    suspend fun getMovieById(id: Int): Flow<Resource<MovieModel>>

}