package com.fernando.gitsearchuser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.fernando.gitsearchuser.ui.screens.GithubUserScreen
import com.fernando.gitsearchuser.ui.theme.GithubSearchUserTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GithubSearchUserTheme {
                App(
                    onFinishApp = {
                        finish()
                    }
                )
            }
        }
    }
}

@Composable
fun App(onFinishApp: () -> Unit) {
    Scaffold { paddingValues ->
        GithubUserScreen(paddingValues, onFinishApp)
    }
}

@Preview
@Composable
private fun AppPreview() {
    App(onFinishApp = {})
}