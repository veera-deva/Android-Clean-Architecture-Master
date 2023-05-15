package com.demo.data.repository.movies

import com.demo.data.api.MovieApi
import com.demo.data.mapper.toDomain
import com.demo.domain.model.NetworkResult
import com.demo.domain.entity.MovieEntity
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(private val movieApi: MovieApi) :
    MovieDataSource {

    override suspend fun getMovies(): NetworkResult<List<MovieEntity>> = try {
        val result = movieApi.getMovieList()
        NetworkResult.Success(result.map { it.toDomain() })
    } catch (e: Exception) {
        NetworkResult.Failure(e)
    }
}