package org.lotka.xenonx.data.dto

import org.lotka.xenonx.data.local.movie.MovieListEntity
import org.lotka.xenonx.data.mapper.toMovieEntity

data class MovieListDto(
    val page: Int,
    val movies: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)


