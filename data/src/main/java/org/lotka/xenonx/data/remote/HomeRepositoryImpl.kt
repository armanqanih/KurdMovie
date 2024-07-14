package org.lotka.xenonx.data.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import org.lotka.xenonx.data.api.MovieApi
import org.lotka.xenonx.data.local.movie.MovieDataBase
import org.lotka.xenonx.data.mapper.toMovie
import org.lotka.xenonx.data.mapper.toMovieEntity
import org.lotka.xenonx.domain.model.MovieModel
import org.lotka.xenonx.domain.repository.HomeRepository
import org.lotka.xenonx.domain.util.Resource
import retrofit2.HttpException
import javax.inject.Inject

class HomeRepositoryImpl@Inject constructor(
    private val movieApi: MovieApi,
    private val movieDatabase: MovieDataBase
): HomeRepository {
    override suspend fun getMovies(fromFetchRemote: Boolean, category: String, page: Int): Flow<Resource<List<MovieModel>>> {
        return flow {
            emit(Resource.Loading(true))

            val localMovieList = movieDatabase.movieDao().getMoviesByCategory(category)
            val shouldLoadLocalMovie = localMovieList.isNotEmpty() && !fromFetchRemote
            if (shouldLoadLocalMovie) {
                emit(Resource.Success(
                    data = localMovieList.map { movieEntity ->
                        movieEntity.toMovie(category)
                    }
                ))
                emit(Resource.Loading(false))
                return@flow
            }

            val movieListFromApi = try {
                movieApi.getMovies(category, page)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies: ${e.localizedMessage}"))
                emit(Resource.Loading(false))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies: ${e.localizedMessage}"))
                emit(Resource.Loading(false))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies: ${e.localizedMessage}"))
                emit(Resource.Loading(false))
                return@flow
            }

            val movieEntities = movieListFromApi?.movies?.map { movieDto ->
                movieDto.toMovieEntity(category)
            } ?: emptyList()

            if (movieEntities.isNotEmpty()) {
                movieDatabase.movieDao().upsertMovieList(movieEntities)
                emit(Resource.Success(
                    movieEntities.map { it.toMovie(category) }
                ))
            } else {
                emit(Resource.Error(message = "No movies found"))
            }

            emit(Resource.Loading(false))
        }
    }


    override suspend fun getMovieById(id: Int): Flow<Resource<MovieModel>> {
       return flow{
           emit(Resource.Loading(true))
           val movie = movieDatabase.movieDao().getMovieById(id)
           if (movie != null){
               emit(Resource.Success(movie.toMovie(movie.category ?: "")))
               emit(Resource.Loading(false))
               return@flow
           }
           emit(Resource.Loading(false))

           emit(Resource.Error(message = "Movie not found"))
       }
    }
}