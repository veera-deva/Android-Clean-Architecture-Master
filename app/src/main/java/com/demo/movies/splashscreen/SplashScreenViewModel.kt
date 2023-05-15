package com.demo.movies.splashscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class SplashScreenViewModel : ViewModel() {

    private val _state = MutableStateFlow<SplashScreenUiState>(SplashScreenUiState.Loading)
    val state: StateFlow<SplashScreenUiState> = _state.asStateFlow()

    init {
        load()
    }

    /*Method to show splashscreen for few milliseconds*/
    private fun load(timeMillis: Long = SPLASH_TIME) {
        viewModelScope.launch {
            delay(timeMillis = timeMillis)
            _state.update { SplashScreenUiState.Success }
        }

    }

    private companion object {
        const val SPLASH_TIME = 3000L
    }
}