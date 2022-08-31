package com.example.poplibhw.repositiry

import com.example.poplibhw.mapper.RepoMapper
import com.example.poplibhw.model.Repo
import com.example.poplibhw.network.RepoApi
import io.reactivex.rxjava3.core.Single

class RepoRepositoryImpl constructor(
    private val repoApi: RepoApi
) : RepoRepository {

    override fun getRepo(login: String): Single<List<Repo>> {
        return repoApi.getRepos(login)
            .map { it.map(RepoMapper::mapToEntity) }
    }

    override fun getReposByLogin(login: String, name: String): Single<Repo> {
        return repoApi.getRepo(login, name)
            .map(RepoMapper::mapToEntity)
    }
}


