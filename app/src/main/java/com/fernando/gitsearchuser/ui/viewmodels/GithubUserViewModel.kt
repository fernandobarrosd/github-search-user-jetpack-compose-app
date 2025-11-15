package com.fernando.gitsearchuser.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fernando.gitsearchuser.models.GithubUser
import com.fernando.gitsearchuser.responses.GithubUserResponse
import com.fernando.gitsearchuser.services.GithubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubUserViewModel(private val githubService: GithubService) : ViewModel() {
    private val _user: MutableLiveData<GithubUser?> = MutableLiveData()
    val user: LiveData<GithubUser?>
        get() = _user

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _errorMessage: MutableLiveData<String?> = MutableLiveData()
    val errorMessage: LiveData<String?>
        get() = _errorMessage

    fun searchUser(username: String) {
        _isLoading.postValue(true)

        githubService.getGithubUserInfo(username).enqueue(object: Callback<GithubUserResponse>{
            override fun onResponse(
                call: Call<GithubUserResponse?>,
                response: Response<GithubUserResponse?>
            ) {
                _isLoading.postValue(false)
                if (response.isSuccessful) {
                    val githubUserResponse = response.body()!!
                    val githubUser = GithubUser(
                        username = githubUserResponse.login,
                        fullName = githubUserResponse.name,
                        bio = githubUserResponse.bio,
                        followersCount = githubUserResponse.followers,
                        followingCount = githubUserResponse.following,
                        profileURL = githubUserResponse.avatarURL,
                        repositoriesURL = "${githubUserResponse.htmlURL}?tab=repositories",
                        userURL = githubUserResponse.htmlURL
                    )
                    _user.postValue(githubUser)
                    _errorMessage.postValue(null)
                    return
                }
                if (response.code() == 404) {
                    _user.postValue(null)
                    _errorMessage.postValue("Usuário não encontrado")
                }
            }

            override fun onFailure(
                call: Call<GithubUserResponse?>,
                t: Throwable
            ) {
                _isLoading.postValue(false)
            }

        })
    }

    class Factory(private val githubService: GithubService) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(GithubUserViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return GithubUserViewModel(githubService) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}