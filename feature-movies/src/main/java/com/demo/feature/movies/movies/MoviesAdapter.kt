package com.demo.feature.movies.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.domain.entity.MovieEntity
import com.demo.feature.movies.databinding.ItemMoviesBinding

/**
 * Adapter class to show list of movies in movies recycler view (@id:rvMovies)
 * */
class MoviesAdapter(
    private val listData: List<MovieEntity>,
    private val onMovieItemClicked: (movieEntity: MovieEntity, itemVIew: View) -> Unit
) :
    RecyclerView.Adapter<MoviesAdapter.MoviesHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        return MoviesHolder(
            ItemMoviesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        holder.bind(listData[position])
    }

    inner class MoviesHolder(private val binding: ItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movieEntity: MovieEntity) {
            with(binding) {
                movies = movieEntity
                root.setOnClickListener {
                    onMovieItemClicked.invoke(movieEntity, it)
                }
            }
        }
    }


}