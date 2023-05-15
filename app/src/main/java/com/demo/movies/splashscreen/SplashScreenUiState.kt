package com.demo.movies.splashscreen

sealed interface SplashScreenUiState {
    object Loading : SplashScreenUiState
    object Success : SplashScreenUiState
}