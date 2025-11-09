package com.fernando.githubsearchuser.responses

import com.google.gson.annotations.SerializedName

data class GithubUserResponse(
    val login: String,
    val name: String?,
    val followers: Int,
    val following: Int,
    val bio: String?,
    @SerializedName("html_url")
    val htmlURL: String,

    @SerializedName("avatar_url")
    val avatarURL: String
)