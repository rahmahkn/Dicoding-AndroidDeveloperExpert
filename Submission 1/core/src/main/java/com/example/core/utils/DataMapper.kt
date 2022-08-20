package com.example.core.utils

import com.example.core.data.source.local.entity.FavoritedStory
import com.example.core.data.source.remote.network.GetStoryResponse
import com.example.core.data.source.remote.network.ListStoryItem
import com.example.core.data.source.remote.network.LoginResponse
import com.example.core.domain.model.GetStoryModel
import com.example.core.domain.model.LoginModel
import com.example.core.domain.model.LoginResultModel
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

    fun mapResponseToDomain(input: List<ListStoryItem>): List<Story> =
        input.map {
            Story(
                id = it.id,
                name = it.name,
                description = it.description,
                createdAt = it.createdAt,
                photoUrl = it.photoUrl
            )
        }

    fun mapResponseToDomain(input: GetStoryResponse): GetStoryModel = GetStoryModel(
        listStory = mapResponseToDomain(input.listStory),
        error = input.error,
        message = input.message
    )

    fun mapResponseToDomain(input: LoginResponse): LoginModel = LoginModel(
        loginResult = LoginResultModel(
            name = input.loginResult.name,
            userId = input.loginResult.userId,
            token = input.loginResult.token
        ),
        error = input.error,
        message = input.message
    )
}