package com.example.poplibhw.network


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

import java.net.URL


data class RepoDto(
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("language")
    val language: String,
    @Expose
    @SerializedName("html_url")
    val htmlUrl: URL,
    @Expose
    @SerializedName("description")
    val description: String,
) 