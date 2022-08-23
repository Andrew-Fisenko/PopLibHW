package com.example.poplibhw.repositiry

import com.example.poplibhw.model.GitHubUser
import io.reactivex.rxjava3.core.Single

interface GitHubRepository {
    fun getUsers(): Single<List<GitHubUser>>
    fun getUser(): Single<GitHubUser>
    fun getUserById(login: String): Single<GitHubUser>
}
