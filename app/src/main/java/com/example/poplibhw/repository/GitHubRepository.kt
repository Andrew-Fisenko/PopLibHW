package com.example.poplibhw.repository

import com.example.poplibhw.model.GitHubUser
import io.reactivex.rxjava3.core.Single

interface GitHubRepository {
    fun getUsers(): Single<List<GitHubUser>>
//    fun getUser(): GitHubUser
    fun getUserByLogin(login: String): Single<GitHubUser>
}

