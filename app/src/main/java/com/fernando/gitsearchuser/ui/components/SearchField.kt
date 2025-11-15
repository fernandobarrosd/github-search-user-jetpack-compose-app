package com.fernando.gitsearchuser.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fernando.gitsearchuser.ui.theme.poppinsFontFamily

@Composable
fun SearchField(
    onSearch: (String) -> Unit,
    modifier: Modifier
) {
    var searchValue by rememberSaveable { mutableStateOf("") }
    var focusColor by remember {
        mutableStateOf(Color.Transparent)
    }

    OutlinedTextField(
        value = searchValue,
        modifier = modifier
            .border(
                width = 3.dp,
                color = focusColor,
                shape = RoundedCornerShape(8.dp)
            )
            .onFocusChanged { focusState ->
                if (focusState.isFocused) {
                    focusColor = Color.Black
                    return@onFocusChanged
                }
                focusColor = Color.Transparent
            },

        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onSearch(searchValue)
            }
        ),
        onValueChange = { value ->
            searchValue = value
        },
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        ),
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 16.sp,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.W400
        ),
        placeholder = {
            Text(
                text = "Usuário do Github",
                color = Color.Black,
                fontSize = 16.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.W400
            )
        },
        prefix = {
            Text(
                text = "@",
                color = Color.Black,
                fontSize = 16.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.W400,
                modifier = Modifier
                    .padding(end = 12.dp)
            )
        },

        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search icon",
                tint = Color.Black,


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