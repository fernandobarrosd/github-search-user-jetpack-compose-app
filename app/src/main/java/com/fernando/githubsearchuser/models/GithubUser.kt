package com.fernando.githubsearchuser.models

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum

data class GithubUser(
    val username: String,
    val fullName: String?,
    val bio: String?,
    val followersCount: Int,
    val followingCount: Int,
    val profileURL: String,
    val repositoriesURL: String,
    val userURL: String
)


val fakeGithubUser = GithubUser(
    username = "fernandobarrosd",
    fullName = "Fernando de Barros",
    bio = LoremIpsum().values.joinToString(" "),
    followersCount = 10,
    followingCount = 10,
    profileURL = "https://github.com/fernandobarrosd.png",
    repositoriesURL = "https://github.com/fernandobarrosd?tab=repositories",
    userURL = "https://github.com/fernandobarrosd"
)