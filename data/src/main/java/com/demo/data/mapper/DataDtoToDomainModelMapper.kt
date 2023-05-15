package com.demo.data.mapper

import com.demo.data.model.MovieResponse
import com.demo.domain.entity.MovieEntity
import com.demo.domain.model.NetworkResult
import javax.inject.Inject

class DataDtoToDomainModelMapper @Inject constructor() :
    Mapper<NetworkResult<List<MovieResponse>>, NetworkResult<List<MovieEntity>>> {

    override fun map(input: NetworkResult<List<MovieResponse>>): NetworkResult<List<MovieEntity>> =
        with(input) {
            val inputData = (input as NetworkResult.Success).data
            NetworkResult.Success(inputData.map { movieResponse -> movieResponse.toMovieEntityReflection() })
        }


    /* fun map(apiResponse: NetworkResult<List<MovieResponse>>): NetworkResult<List<MovieEntity>> {
         val inputData = (apiResponse as NetworkResult.Success).data
         return NetworkResult.Success(inputData.map { movieResponse -> movieResponse.toMovieEntityReflection() })
     }*/

    private fun MovieResponse.toMovieEntityReflection() = with(::MovieEntity) {
        val propertiesByName = MovieResponse::class.members.associateBy { it.name }
        callBy(parameters.associateWith { parameter ->
            when (parameter.name) {
                MovieEntity::id.name -> id
                MovieEntity::title.name -> title
                MovieEntity::description.name -> description
                MovieEntity::image.name -> image
                MovieEntity::category.name -> category
                else -> propertiesByName[parameter.name]
            }
        })
    }


}