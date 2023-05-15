package com.demo.feature.movies.movies

import com.demo.domain.entity.MovieEntity

/**
 * Ui State for MoviesFragment
 * */
sealed interface MoviesUIState {
    object Loading : MoviesUIState
    data class Success(
        val moviesList: List<MovieEntity> = emptyList()
    ) : MoviesUIState

    data class Error(val errorMessage: String? = null) : MoviesUIState

}