package com.fernando.githubsearchuser.services

import com.fernando.githubsearchuser.BuildConfig
import com.fernando.githubsearchuser.responses.GithubUserResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("/users/{username}")
    fun getGithubUserInfo(@Path("username") username: String) : Call<GithubUserResponse>


    companion object {
        private val httpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                val apiToken = BuildConfig.GITHUB_API_TOKEN

                val newRequest = request.newBuilder()
                    .header("Authorization", "Bearer $apiToken")
                    .build()

                chain.proceed(newRequest)
            }
            .build()
        val githubRetrofit: Retrofit = Retrofit
            .Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()


        val githubService = githubRetrofit.create(GithubService::class.java)
    }
}