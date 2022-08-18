package com.example.poplibhw.repositiry

import com.example.poplibhw.model.GitHubUser

class GitHubRepositoryImpl : GitHubRepository {

    private val repositories = listOf(
        GitHubUser(1,"Billy"),
        GitHubUser(2, "Malm"),
        GitHubUser(3, "Hemnes"),
        GitHubUser(4, "Blojai"),
        GitHubUser(5, "Barnes")
    )

    override fun getUsers(): List<GitHubUser> {
        return repositories
    }

    override fun getUser(): GitHubUser {
        return repositories[0]
    }

    override fun getUserById(id: Int): GitHubUser? {
        return repositories.find { it.id == id }
    }

}
