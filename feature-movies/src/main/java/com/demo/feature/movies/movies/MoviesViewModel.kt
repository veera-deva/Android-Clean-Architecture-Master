package com.demo.feature.movies.movies

import androidx.lifecycle.viewModelScope
import com.demo.common_ui.base.BaseViewModel
import com.demo.common_ui.utils.Constants
import com.demo.domain.entity.MovieEntity
import com.demo.domain.model.NetworkResult
import com.demo.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * View model for MoviesFragment
 * */
@HiltViewModel
class MoviesViewModel @Inject constructor(private val moviesUseCase: MovieUseCase) :
    BaseViewModel() {

    private val _moviesUiState = MutableStateFlow<MoviesUIState>(MoviesUIState.Loading)
    val moviesUiState: StateFlow<MoviesUIState> = _moviesUiState.asStateFlow()

    init {
        getMovies()
    }

    fun getMovies() = viewModelScope.launch {
        _moviesUiState.value = MoviesUIState.Loading
        moviesUseCase().collect { response ->
            when (response) {
                is NetworkResult.Success -> {
                    _moviesUiState.value = MoviesUIState.Success(response.data)
                }

                is NetworkResult.Failure -> {
                    _moviesUiState.value = MoviesUIState.Error(response.e.message)
                }

                else -> {
                    _moviesUiState.value = MoviesUIState.Error(Constants.DEFAULT_ERROR_MESSAGE)
                }
            }
        }
    }
}