package com.example.poplibhw.mapper

import com.example.poplibhw.model.GitHubUser
import com.example.poplibhw.network.UserDto

object UserMapper {

    fun mapToEntity(dto: UserDto): GitHubUser {
        return GitHubUser(
            id = dto.id,
            login = dto.login,
            avatarUrl = dto.avatarUrl
        )
    }
}