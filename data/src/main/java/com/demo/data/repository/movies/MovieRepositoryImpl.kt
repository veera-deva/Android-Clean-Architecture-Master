package com.demo.data.repository.movies

import com.demo.domain.model.NetworkResult
import com.demo.domain.entity.MovieEntity
import com.demo.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val dataSource: MovieDataSource) :
    MovieRepository {

    override fun getMovies(): Flow<NetworkResult<List<MovieEntity>>> {
        return flow {
            emit(dataSource.getMovies())
        }.flowOn(Dispatchers.IO)

    }
}