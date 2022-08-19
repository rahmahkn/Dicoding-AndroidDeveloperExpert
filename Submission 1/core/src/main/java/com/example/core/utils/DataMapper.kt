package com.example.core.utils

import com.example.core.data.source.local.entity.FavoritedStory
import com.example.core.domain.model.Story

object DataMapper {
    fun mapEntitiesToDomain(input: List<FavoritedStory>): List<Story> =
        input.map {
            Story(
                id = it.id,
                name = it.name,
                description = it.description,
                createdAt = it.createdAt,
                photoUrl = it.photoUrl
            )
        }

    fun mapDomainToEntity(it: Story) = FavoritedStory(
        id = it.id,
        name = it.name,
        description = it.description,
        createdAt = it.createdAt,
        photoUrl = it.photoUrl
    )

    fun mapEntityToDomain(it: FavoritedStory) = Story(
        id = it.id,
        name = it.name,
        description = it.description,
        createdAt = it.createdAt,
        photoUrl = it.photoUrl
    )
}