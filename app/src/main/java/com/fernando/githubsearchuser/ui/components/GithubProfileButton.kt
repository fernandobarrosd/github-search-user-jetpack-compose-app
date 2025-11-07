package com.fernando.githubsearchuser.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fernando.githubsearchuser.ui.theme.Violet500
import com.fernando.githubsearchuser.ui.theme.poppinsFontFamily

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