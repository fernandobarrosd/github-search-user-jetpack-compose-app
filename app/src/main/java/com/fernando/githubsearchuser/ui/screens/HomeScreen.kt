package com.fernando.githubsearchuser.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fernando.githubsearchuser.models.fakeGithubUser
import com.fernando.githubsearchuser.ui.components.GithubProfile
import com.fernando.githubsearchuser.ui.components.SearchField
import com.fernando.githubsearchuser.ui.theme.Violet700

@Composable
fun HomeScreen(paddingValues: PaddingValues) {
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
                Log.i("SEARCH_VALUE", searchValue)
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.size(71.dp))
        GithubProfile(
            user = fakeGithubUser
        )
    }
}


@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(PaddingValues())
}
