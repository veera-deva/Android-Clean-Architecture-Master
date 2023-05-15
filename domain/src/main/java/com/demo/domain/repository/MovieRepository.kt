package com.demo.domain.repository

import com.demo.domain.entity.MovieEntity
import com.demo.domain.model.NetworkResult
import kotlinx.coroutines.flow.Flow

fun interface MovieRepository {
    fun getMovies(): Flow<NetworkResult<List<MovieEntity>>>
}