package com.demo.movies.splashscreen

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.demo.common_ui.base.BaseFragment
import com.demo.common_ui.utils.launchAndRepeatWithLifeCycle
import com.demo.movies.databinding.FragmentSplashScreenBinding
import kotlinx.coroutines.launch

class SplashScreenFragment :
    BaseFragment<FragmentSplashScreenBinding>(FragmentSplashScreenBinding::inflate) {
    private val splashViewModel by viewModels<SplashScreenViewModel>()

    override fun setUpView() {
        activity?.actionBar?.hide()
        subscribeUI()

    }

    private fun subscribeUI() {
        with(splashViewModel) {
            launchAndRepeatWithLifeCycle {
                launch {
                    state.collect { uiState ->
                        when (uiState) {
                            is SplashScreenUiState.Loading -> {
                                showLoading()
                            }

                            is SplashScreenUiState.Success -> {
                                hideLoading()
                                findNavController().navigate(com.demo.feature.movies.R.id.nav_movies)
                            }
                        }
                    }
                }
            }
        }
    }
}