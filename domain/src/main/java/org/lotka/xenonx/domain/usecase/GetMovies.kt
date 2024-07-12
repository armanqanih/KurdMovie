package org.lotka.xenonx.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.lotka.xenonx.domain.model.MovieModel
import org.lotka.xenonx.domain.repository.HomeRepository
import org.lotka.xenonx.domain.util.Resource
import javax.inject.Inject


class GetMovies @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(fromFetchRemote: Boolean, category: String, page: Int): Flow<Resource<List<MovieModel>>> {
        return homeRepository.getMovies(fromFetchRemote,category, page)
    }

}