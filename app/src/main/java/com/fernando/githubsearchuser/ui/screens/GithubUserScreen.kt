package com.fernando.githubsearchuser.ui.screens

import android.app.Activity
import android.content.res.Configuration
import android.net.ConnectivityManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fernando.githubsearchuser.services.GithubService
import com.fernando.githubsearchuser.ui.components.GithubProfile
import com.fernando.githubsearchuser.ui.components.GithubProfileSkeleton
import com.fernando.githubsearchuser.ui.components.SearchField
import com.fernando.githubsearchuser.ui.theme.Violet700
import com.fernando.githubsearchuser.ui.theme.poppinsFontFamily
import com.fernando.githubsearchuser.ui.viewmodels.GithubUserViewModel
import com.fernando.githubsearchuser.ui.viewmodels.InternetCheckerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GithubUserScreen(paddingValues: PaddingValues, onFinishApp: () -> Unit) {
    val context = LocalContext.current
    val connectivityManager = context.getSystemService(ConnectivityManager::class.java)

    val githubUserViewModel = viewModel<GithubUserViewModel>(
        factory = GithubUserViewModel.Factory(GithubService.githubService)
    )
    val internetCheckerViewModel = viewModel<InternetCheckerViewModel>(
        factory = InternetCheckerViewModel.Factory(connectivityManager)
    )
    val githubUser by githubUserViewModel.user.observeAsState(null)
    val isLoading by githubUserViewModel.isLoading.observeAsState(false)
    val errorMessage by githubUserViewModel.errorMessage.observeAsState(null)
    val isConnected by internetCheckerViewModel.isConnected.collectAsState(false)


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Violet700)
            .padding(paddingValues)
            .padding(20.dp)
            .fillMaxSize()

    ) {
        if (!isConnected) {
            AlertDialog(
                title = {
                    Text(
                        text = "Erro de conexão",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.W400,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                },
                text =  {
                    Text(
                        text = "Conexão de internet indisponivel, feche o aplicativo, conecte-se e tente novamente",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            onFinishApp()
                        }
                    ) {
                        Text(
                            text = "Sair"
                        )
                    }
                },
                dismissButton = {},
                onDismissRequest = {}
            )
        }
        SearchField(
            onSearch = { searchValue ->
                githubUserViewModel.searchUser(searchValue)
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
    GithubUserScreen(
        paddingValues = PaddingValues(),
        onFinishApp = {}
    )
}
