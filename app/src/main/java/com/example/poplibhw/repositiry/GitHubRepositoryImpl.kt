package com.example.poplibhw.repositiry

import com.example.poplibhw.model.GitHubUser

class GitHubRepositoryImpl : GitHubRepository {

    private val repositories = listOf(
        GitHubUser("MrFox"),
        GitHubUser("VictorMelnik"),
        GitHubUser("Denix"),
        GitHubUser("DmitryWb"),
        GitHubUser("Larisa")
    )

    override fun getUsers(): List<GitHubUser> {
        return repositories
    }
}
