package com.example.poplibhw.repositiry

import com.example.poplibhw.model.GitHubUser
import io.reactivex.rxjava3.core.Single

class GitHubRepositoryImplOld : GitHubRepository {

    private val repositories = listOf(
        GitHubUser(1, "Billy", null),
        GitHubUser(2, "Malm", null),
        GitHubUser(3, "Hemnes", null),
        GitHubUser(4, "Blojai", null),
        GitHubUser(5, "Barnes", null)
    )

    override fun getUsers(): Single<List<GitHubUser>> {
        return Single.create {
            it.onSuccess(repositories)
        }
    }

    override fun getUser(): Single<GitHubUser> {
        val i = (0..10).random()
        return if (i < 5) {
            Single.create {
                it.onSuccess(repositories[0])
            }
        } else {
            Single.create {
                it.onError(error("Error!"))
            }
        }
    }

    override fun getUserById(login: String): Single<GitHubUser> {
        return Single.create {
            it.onSuccess(repositories[0])
        }
    }

    fun getUserById(id: Int): GitHubUser {
        return repositories[id]
    }
}

