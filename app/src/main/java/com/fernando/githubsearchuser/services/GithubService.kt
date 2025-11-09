package com.fernando.githubsearchuser.services

import com.fernando.githubsearchuser.responses.GithubUserResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("/users/{username}")
    fun getGithubUserInfo(@Path("username") username: String) : Call<GithubUserResponse>


    companion object {
        val githubRetrofit: Retrofit = Retrofit
            .Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val githubService = githubRetrofit.create(GithubService::class.java)
    }
}