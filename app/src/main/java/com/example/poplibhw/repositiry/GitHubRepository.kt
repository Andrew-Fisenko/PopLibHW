package com.example.poplibhw.repositiry
import com.example.poplibhw.model.GitHubUser

interface GitHubRepository {
    fun getUsers(): List<GitHubUser>
    fun getUser(): GitHubUser
    fun getUserById(id : Int) : GitHubUser?
}
