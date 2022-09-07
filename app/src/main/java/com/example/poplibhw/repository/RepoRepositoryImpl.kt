package com.example.poplibhw.repository

import com.example.poplibhw.mapper.RepoMapper
import com.example.poplibhw.model.Repo
import com.example.poplibhw.network.RepoApi
import io.reactivex.rxjava3.core.Single

class RepoRepositoryImpl constructor(
    private val repoApi: RepoApi
) : RepoRepository {

    override fun getReposByUserLogin(login: String): Single<List<Repo>> {
        return repoApi.getUserRepos(login)
            .map { it.map(RepoMapper::mapToEntity) }
    }

    override fun getRepoByName(login: String, name: String): Single<Repo> {
        return repoApi.gerUserRepo(login, name)
            .map(RepoMapper::mapToEntity)
    }
}


