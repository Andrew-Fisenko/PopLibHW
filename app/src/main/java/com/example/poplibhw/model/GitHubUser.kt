package com.example.poplibhw.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitHubUser(
    val id: Int,
    val login: String,
    val avatarUrl: String?
) : Parcelable
