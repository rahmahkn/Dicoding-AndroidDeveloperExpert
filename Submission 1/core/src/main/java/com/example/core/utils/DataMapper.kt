package com.dicoding.tourismapp.core.utils

import com.dicoding.tourismapp.core.domain.model.Story
import com.example.core.data.source.local.entity.FavoritedStory
import com.example.myapplication.model.domain.GetStoryResponse

object DataMapper {
    fun mapResponsesToDomain(input: GetStoryResponse): List<Story> {
        val storyList = ArrayList<Story>()
        input.listStory.map {
            val story = Story(
                id = it.id,
                name = it.name,
                description = it.description,
                createdAt = it.createdAt,
                photoUrl = it.photoUrl
            )
            storyList.add(story)
        }
        return storyList
    }

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