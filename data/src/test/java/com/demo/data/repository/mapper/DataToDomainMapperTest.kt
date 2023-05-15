package com.demo.data.repository.mapper

import com.demo.data.mapper.toDomain
import com.demo.data.model.MovieResponse
import org.junit.Assert
import org.junit.Test


class DataToDomainMapperTest {

    private fun buildMovieResponse(): MovieResponse =
        MovieResponse(
            id = 2,
            title = "Mock title 2",
            description = "Mock Description 2",
            image = "Mock url 2",
            category = "Mock category 2"
        )

    @Test
    fun `map MovieResponse to MovieEntity`() {
        val mockedData = buildMovieResponse()
        val entity = mockedData.toDomain()
        Assert.assertEquals(2, entity.id)
        Assert.assertEquals("Mock title 2", entity.title)
        Assert.assertEquals("Mock Description 2", entity.description)
        Assert.assertEquals("Mock url 2", entity.image)
        Assert.assertEquals("Mock category 2", entity.category)
    }
}