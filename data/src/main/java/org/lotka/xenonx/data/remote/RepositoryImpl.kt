package org.lotka.xenonx.data.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.lotka.xenonx.data.api.MovieApi
import org.lotka.xenonx.data.dto.toDomain
import org.lotka.xenonx.domain.ErrorMessage2
import org.lotka.xenonx.domain.model.MovieListModel
import org.lotka.xenonx.domain.repository.HomeRepository
import org.lotka.xenonx.domain.util.ResultState
import javax.inject.Inject

class RepositoryImpl@Inject constructor(
    private val movieApi: MovieApi
): HomeRepository {
    override suspend fun getMovies(fromFetchRemote: Boolean,category: String, page: Int): Flow<ResultState<MovieListModel>> {
        return flow {
            try {
                val movieList = movieApi.getMovies(category, page).toDomain()
                emit(ResultState.Success(movieList))
            } catch (e: Exception) {
               val error = e.printStackTrace()
                emit(ResultState.Error(ErrorMessage2(error("please check your internet connection\"$error"))))
            }


        }
    }
}