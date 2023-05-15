package com.demo.feature.movies.movies

import app.cash.turbine.test
import com.demo.domain.entity.MovieEntity
import com.demo.domain.model.NetworkResult
import com.demo.domain.usecase.MovieUseCase
import com.demo.shared_test.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    private lateinit var testObject: MoviesViewModel

    @Mock
    private lateinit var movieUseCase: MovieUseCase

    @get:Rule
    var testCoroutineRUle = MainCoroutineRule()

    @Before
    fun setUp() {
        testObject = MoviesViewModel(movieUseCase)
    }

    @Test
    fun `onMoviesViewModel init validate is loading`() {
        assertEquals(MoviesUIState.Loading, testObject.moviesUiState.value)
    }

    @Test
    fun `onMoviesViewModel getMovies() returns list of movies`() =
        runTest {
            val movieList = mockedMovieList()
            testObject.moviesUiState.test {
                val firstItem = awaitItem()
                assertEquals(MoviesUIState.Loading, firstItem)
                `when`(movieUseCase()).thenReturn(flow { emit(NetworkResult.Success(movieList)) })
                testObject.getMovies()
                val secondItem = awaitItem()
                assertEquals(MoviesUIState.Success(movieList), secondItem)
            }
        }

    @Test
    fun `onMoviesViewModel init validate movies list data is empty`() {
        runTest {
            val movieList = emptyList<MovieEntity>()
            testObject.moviesUiState.test {
                val firstItem = awaitItem()
                assertEquals(MoviesUIState.Loading, firstItem)
                `when`(movieUseCase.invoke())
                    .thenReturn(flow { emit(NetworkResult.Success(movieList)) })
                testObject.getMovies()
                val secondItem = awaitItem()
                assertEquals(MoviesUIState.Success(movieList), secondItem)
            }
        }
    }

    @Test
    fun `onMoviesViewModel init validate getMovies return error`() {
        runTest {
            testObject.moviesUiState.test {
                val firstItem = awaitItem()
                assertEquals(MoviesUIState.Loading, firstItem)
                `when`(movieUseCase())
                    .thenReturn(flow { emit(NetworkResult.Failure(Exception("Network Error"))) })
                val secondItem = awaitItem()
                assertEquals(MoviesUIState.Error("Network Error"), secondItem)
            }
        }
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