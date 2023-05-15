package com.demo.domain.usecase

import com.demo.domain.entity.MovieEntity
import com.demo.domain.model.NetworkResult
import com.demo.domain.repository.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetMoviesUseCaseTest {

    @Mock
    lateinit var movieRepository: MovieRepository
    lateinit var movieUseCase: MovieUseCase


    @org.junit.Before
    fun setUp() {
        movieUseCase = GetMoviesUseCaseImpl(movieRepository)
    }

    @Test
    fun `onGetMovieUseCaseImpl invoke should return movie list`() = runTest {
        val mockedResponse = mockedMovieList()
        Mockito.`when`(movieRepository.getMovies())
            .thenReturn(flowOf(NetworkResult.Success(mockedResponse)))
        val useCase = movieUseCase.invoke().toList()
        assert(useCase.isNotEmpty())
    }

    private fun mockedMovieList() = listOf(
        MovieEntity(
            1,
            "Mock title",
            "Mock Description",
            "mockUrl", "mockCategory"
        )
    )
}