package org.lotka.xenonx.domain.model

data class MovieListModel(
    val page: Int,
    val movies: List<MovieModel>,
    val total_pages: Int,
    val total_results: Int
)