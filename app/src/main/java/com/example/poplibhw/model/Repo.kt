package com.example.poplibhw.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.net.URL

@Parcelize
data class Repo(
    val id: Int,
    val name: String,
    val htmlUrl: URL?,
    val language: String?,
    val description: String?,
) : Parcelable
