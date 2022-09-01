package com.example.poplibhw.repository

import com.example.poplibhw.model.Repo
import io.reactivex.rxjava3.core.Single

interface RepoRepository {
    fun getReposByUserLogin(login: String): Single<List<Repo>>
    fun getRepoByName(login: String, name: String): Single<Repo>
}
