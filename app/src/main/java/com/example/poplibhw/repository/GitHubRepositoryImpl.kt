package com.example.poplibhw.repository

import com.example.poplibhw.mapper.UserMapper
import com.example.poplibhw.model.GitHubUser
import com.example.poplibhw.network.UserApi
import io.reactivex.rxjava3.core.Single

class GitHubRepositoryImpl constructor(
    private val userApi: UserApi
) : GitHubRepository {

    override fun getUsers(): Single<List<GitHubUser>> {
        return userApi.getAllUsers()
            .map { it.map(UserMapper::mapToEntity) }
    }
//
//    override fun getUser(): GitHubUser {
//        return GitHubUser(0, "", "")
//    }

    override fun getUserByLogin(login: String): Single<GitHubUser> {
//        return userApi.getUser(login)
//            .map(UserMapper::mapToEntity)
        val i = (0..100).random()
        return if (i < 90) {
            userApi.getUser(login)
                .map(UserMapper::mapToEntity)
        } else {
            Single.create {
                it.onError(error("Error!"))
            }
        }
    }
}



