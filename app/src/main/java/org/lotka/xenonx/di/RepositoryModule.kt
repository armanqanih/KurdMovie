package org.lotka.xenonx.di


import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.lotka.xenonx.data.remote.HomeRepositoryImpl
import org.lotka.xenonx.domain.repository.HomeRepository
import javax.inject.Singleton

/**
 * @author Android Devs Academy (Arman sherwanii)
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieListRepository(
        movieListHomeRepositoryImpl: HomeRepositoryImpl
    ): HomeRepository

}












