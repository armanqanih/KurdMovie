package org.lotka.xenonx.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.lotka.xenonx.domain.model.MovieModel
import org.lotka.xenonx.domain.repository.HomeRepository
import org.lotka.xenonx.domain.util.Resource
import javax.inject.Inject

class GetMovieById @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(id: Int): Flow<Resource<MovieModel>>{
        return homeRepository.getMovieById(id)
    }

}