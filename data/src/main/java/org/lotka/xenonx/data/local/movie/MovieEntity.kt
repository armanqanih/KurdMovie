package org.lotka.xenonx.data.local.movie

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movie")
class MovieEntity (
    @PrimaryKey
    val id: Int?,

    val category: String?,
    val adult: Boolean?,
    val backdrop_path: String?,
    val genre_ids: String?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?
)


data class MovieListEntity(
    val page: Int,
    val movies: List<MovieEntity>,
    val total_pages: Int,
    val total_results: Int
)

