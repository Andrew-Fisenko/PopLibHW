package com.example.poplibhw.mapper

import com.example.poplibhw.model.Repo
import com.example.poplibhw.network.RepoDto

object RepoMapper {

    fun mapToEntity(dto: RepoDto): Repo {
        return Repo(
            id = dto.id,
            name = dto.name,
            htmlUrl = dto.htmlUrl,
            language = dto.language,
            description = dto.description
        )
    }
}