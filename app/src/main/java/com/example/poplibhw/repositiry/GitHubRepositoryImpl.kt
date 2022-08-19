package com.example.poplibhw.repositiry

import com.example.poplibhw.model.GitHubUser
import io.reactivex.rxjava3.core.Single

class GitHubRepositoryImpl : GitHubRepository {

    private val repositories = listOf(
        GitHubUser(1, "Billy"),
        GitHubUser(2, "Malm"),
        GitHubUser(3, "Hemnes"),
        GitHubUser(4, "Blojai"),
        GitHubUser(5, "Barnes")
    )

    override fun getUsers(): Single<List<GitHubUser>> {
        return Single.create {
            it.onSuccess(repositories)
        }
    }

    override fun getUser(): Single<GitHubUser> {
        var i = (0..10).random()
        return if (i <2) {
            Single.create {
                it.onSuccess(repositories[0])
            }
        } else {
            Single.create {
                it.onError(error("Error!"))
            }
        }
    }

    override fun getUserById(id: Int): GitHubUser {
        return repositories[id]
    }
}

