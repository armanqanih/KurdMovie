package org.lotka.xenonx.data.dto

import org.lotka.xenonx.domain.model.MovieListModel

data class MovieListDto(
    val page: Int,
    val movies: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)


fun MovieListDto.toDomain() = MovieListModel(
    page = page,
    movies = movies.map { it.toDomain() },
    total_pages = total_pages,
    total_results = total_results
)