package com.fernando.githubsearchuser.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fernando.githubsearchuser.services.GithubService
import com.fernando.githubsearchuser.ui.components.GithubProfile
import com.fernando.githubsearchuser.ui.components.GithubProfileSkeleton
import com.fernando.githubsearchuser.ui.components.SearchField
import com.fernando.githubsearchuser.ui.theme.Violet700
import com.fernando.githubsearchuser.ui.theme.poppinsFontFamily
import com.fernando.githubsearchuser.ui.viewmodels.GithubUserViewModel

@Composable
fun GithubUserScreen(paddingValues: PaddingValues) {
    val githubUserViewModel = viewModel<GithubUserViewModel>(
        factory = GithubUserViewModel.Factory(GithubService.githubService)
    )
    val githubUser by githubUserViewModel.user.observeAsState(null)
    val isLoading by githubUserViewModel.isLoading.observeAsState(false)
    val errorMessage by githubUserViewModel.errorMessage.observeAsState(null)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Violet700)
            .fillMaxSize()
            .padding(paddingValues)
            .padding(20.dp)
    ) {
        SearchField(
            onSearch = { searchValue ->
                githubUserViewModel.searchUser(searchValue.trim())
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxHeight()
        ) {
            if (isLoading) {
                GithubProfileSkeleton()
                return@Column
            }
            errorMessage?.let { errorMessage ->
                Text(
                    text = errorMessage,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
            githubUser?.let { user ->
                GithubProfile(
                    user = user
                )
            }
        }
    }
}


@Preview
@Composable
private fun GithubUserScreenPreview() {
    GithubUserScreen(PaddingValues())
}
