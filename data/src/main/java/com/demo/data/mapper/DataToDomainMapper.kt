package com.demo.data.mapper

import com.demo.data.model.MovieResponse
import com.demo.domain.entity.MovieEntity


fun MovieResponse.toDomain() = MovieEntity(
    id = id, image = image, title = title, description = description, category = category
)