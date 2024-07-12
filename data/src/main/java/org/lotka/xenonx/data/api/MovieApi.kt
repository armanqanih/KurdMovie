package org.lotka.xenonx.data.api

import org.lotka.xenonx.data.dto.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApi {

    @GET("movie/{category}")
    suspend fun getMovies(
        @Path("category") category: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY
    ):MovieListDto






    companion object{
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val Image_BASE_URL = "https://image.tmdb.org/t/p/w500/"
        const val API_KEY = "1e4c5f50d39b0ea9193cc77d8807594c"
    }

}