package com.demo.feature.movies.movies

import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.demo.common_ui.base.BaseFragment
import com.demo.common_ui.utils.launchAndRepeatWithLifeCycle
import com.demo.domain.entity.MovieEntity
import com.demo.feature.movies.databinding.FragmentMoviesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * Movies fragment to display list of movies
 */
@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>(FragmentMoviesBinding::inflate) {
    private val moviesViewModel by viewModels<MoviesViewModel>()


    override fun setUpView() {
        subscribeUI()
        setUpRecyclerView()
        binding.btnRetry.setOnClickListener {
            moviesViewModel.getMovies()
        }

        handleOnBackPress()
    }

    private fun setUpRecyclerView() {
        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(
                requireContext(),
                3,
                GridLayoutManager.VERTICAL,
                false
            )
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(requireContext(), GridLayoutManager.VERTICAL))
        }
    }

    private fun subscribeUI() {
        launchAndRepeatWithLifeCycle {
            launch {
                moviesViewModel.moviesUiState.collect { uiState ->
                    when (uiState) {
                        is MoviesUIState.Loading -> {
                            showLoading()
                        }

                        is MoviesUIState.Success -> {
                            hideLoading()
                            binding.rvMovies.adapter =
                                MoviesAdapter(
                                    uiState.moviesList,
                                    onMovieItemClicked = onMovieItemClicked
                                )
                        }

                        is MoviesUIState.Error -> {
                            hideLoading()
                            binding.llNoDataView.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    private val onMovieItemClicked: (movieEntity: MovieEntity, itemVIew: View) -> Unit =
        { movieEntity, _ ->
            val action =
                MoviesFragmentDirections.actionMovieFragmentToMovieDetailsFragment(movieEntity)
            findNavController().navigate(action)
        }


    private fun handleOnBackPress() {
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }
}