package com.fernando.githubsearchuser.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun GithubProfileButtonSkeleton() {
    Card (
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .width(100.dp)
            .height(36.dp)
    ) {
    }
}

@Preview
@Composable
private fun GithubProfileSkeletonPreview() {
    GithubProfileSkeleton()
}

@Composable
fun GithubProfileSkeleton() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(120.dp)
                .background(Color.White)
        ) {}

        Spacer(Modifier.size(16.dp))

        Box(
            modifier = Modifier
                .width(100.dp)
                .height(20.dp)
                .background(Color.White)
        ) {}
        Spacer(Modifier.size(8
            .dp))
        Box(
            modifier = Modifier
                .width(100.dp)
                .height(20.dp)
                .background(Color.White)
        ) {}
        Spacer(Modifier.size(48.dp))
        Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            modifier = Modifier
                .width(350.dp)
                .height(200.dp)
        ) {}

        Spacer(Modifier.size(56.dp))
        Row {
            GithubProfileButtonSkeleton()
            Spacer(Modifier.size(24.dp))
            GithubProfileButtonSkeleton()
        }
    }
}