package com.demo.data.di

import com.demo.data.repository.movies.MovieDataSource
import com.demo.data.repository.movies.MovieRemoteDataSource
import com.demo.data.repository.movies.MovieRepositoryImpl
import com.demo.domain.repository.MovieRepository
import com.demo.domain.usecase.GetMoviesUseCaseImpl
import com.demo.domain.usecase.MovieUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    internal abstract fun bindRepository(repositoryImpl: MovieRepositoryImpl): MovieRepository

    @Binds
    internal abstract fun bindDataSource(movieRemoteDataSource: MovieRemoteDataSource): MovieDataSource

    @Binds
    internal abstract fun bindMovieUseCase(getMoviesUseCase: GetMoviesUseCaseImpl): MovieUseCase
}