package com.example.poplibhw.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoApi {

    @GET("/users/{login}/repos")
    fun getRepos(@Path("login") login: String): Single<List<RepoDto>>

    @GET("/repos/{login}/{repo}")
    fun getRepo(@Path("login") login: String, @Path("repo") repo: String): Single<RepoDto>
}