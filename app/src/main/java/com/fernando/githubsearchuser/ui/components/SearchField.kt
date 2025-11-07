package com.fernando.githubsearchuser.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchField(
    onSearch: (String) -> Unit,
    modifier: Modifier
) {
    var searchValue by remember { mutableStateOf("") }

    TextField(
        value = searchValue,
        modifier = modifier
            .onKeyEvent { keyEvent ->
                if (keyEvent.key == Key.Enter) {
                    onSearch(searchValue)
                    true
                }
                false
            },
        onValueChange = { value ->
            searchValue = value
        },
        shape = RoundedCornerShape(8.dp),
        maxLines = 1,
        minLines = 1,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search icon",

                modifier = Modifier
                    .clickable {
                        onSearch(searchValue)
                    }
            )
        }
    )
}

@Preview
@Composable
private fun SearchFieldPreview() {
    SearchField(modifier = Modifier.width(400.dp), onSearch = {})
}