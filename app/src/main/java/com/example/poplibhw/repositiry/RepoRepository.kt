package com.example.poplibhw.repositiry

import com.example.poplibhw.model.Repo
import io.reactivex.rxjava3.core.Single

interface RepoRepository {
    fun getRepo(login: String): Single<List<Repo>>
    fun getReposByLogin(login: String, name: String): Single<Repo>
}
