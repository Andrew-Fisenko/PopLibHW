package com.example.poplibhw.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserDto(
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("login")
    val login: String,
    @Expose
    @SerializedName("avatar_url")
    val avatarUrl: String
)