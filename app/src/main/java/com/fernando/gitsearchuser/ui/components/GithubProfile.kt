package com.fernando.gitsearchuser.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.fernando.gitsearchuser.models.GithubUser
import com.fernando.gitsearchuser.models.fakeGithubUser
import com.fernando.gitsearchuser.ui.theme.Violet500
import com.fernando.gitsearchuser.ui.theme.poppinsFontFamily

@Composable
fun GithubProfileButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Violet500
        ),
        contentPadding = PaddingValues(
            horizontal = 36.dp
        )
    ) {
        Text(
            text = text,
            color = Color.White,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.W400
        )
    }
}

@Preview
@Composable
private fun GithubProfileButtonPreview() {
    GithubProfileButton("Perfil") {}
}


@Composable
fun GithubProfile(user: GithubUser) {
    val uriHandler = LocalUriHandler.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SubcomposeAsyncImage(
            model = user.profileURL,
            contentDescription = "Github profile",
            modifier = Modifier
                .clip(CircleShape)
                .size(120.dp),
            loading = {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(120.dp)
                        .background(Color.White)
                ) {}
            }
        )
        if (user.fullName != null) {
            Spacer(Modifier.size(16.dp))
            Text(
                text = user.fullName,
                color = Color.White,
                fontSize = 14.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.W400
            )
        }
        Text(
            text = "@${user.username}",
            color = Color.White,
            fontSize = 12.sp,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.size(48.dp))
        if (user.bio != null) {
            Card(
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Violet500
                )
            ) {
                Text(
                    text = user.bio,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.W400,
                    modifier = Modifier
                        .width(350.dp)
                        .heightIn(max  = 200.dp)
                        .padding(20.dp)
                        .verticalScroll(rememberScrollState())
                )
            }
            Spacer(Modifier.size(56.dp))
        }
        Row {
            GithubProfileButton(
                text = "Perfil",
                onClick = {
                    uriHandler.openUri(user.userURL)
                }
            )
            Spacer(Modifier.size(24.dp))
            GithubProfileButton(
                text = "Repositórios",
                onClick = {
                    uriHandler.openUri(user.repositoriesURL)
                }
            )

        }
    }
}

@Preview
@Composable
private fun GithubProfilePreview() {
    GithubProfile(
        user = fakeGithubUser
    )
}