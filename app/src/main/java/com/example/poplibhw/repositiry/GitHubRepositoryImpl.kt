package com.example.poplibhw.repositiry

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

    override fun getUser(): Single<GitHubUser> {
        TODO("Not yet implemented")
    }

    override fun getUserById(login: String): Single<GitHubUser> {
        val i = (0..10).random()
        return if (i < 7) {
            userApi.getUser(login)
                .map(UserMapper::mapToEntity)
        } else {
            Single.create {
                it.onError(error("Error!"))
            }
        }
    }
}



