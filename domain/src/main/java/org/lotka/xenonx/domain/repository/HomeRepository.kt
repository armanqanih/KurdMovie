package org.lotka.xenonx.domain.repository

import kotlinx.coroutines.flow.Flow
import org.lotka.xenonx.domain.model.MovieListModel
import org.lotka.xenonx.domain.util.ResultState

interface HomeRepository {


    suspend fun getMovies(fromFetchRemote: Boolean,category: String, page: Int): Flow<ResultState<MovieListModel>>

}