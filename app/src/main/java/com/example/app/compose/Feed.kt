package com.example.app.compose

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.app.network.RickAndMortyCharacter

@Composable
fun Feed(
    modifier: Modifier = Modifier,
    posts: List<RickAndMortyCharacter>,
    showLoadingButton: Boolean,
    @StringRes loadingProgressText: Int?,
    @StringRes loadButtonText: Int,
    loadButtonClick: () -> Unit = {}
) {

    val listState = rememberLazyListState()

    LazyColumn(
        modifier = modifier,
        state = listState
    ) {
        items(posts) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(model = it.image, contentDescription = "image of ${it.name}")
                Spacer(modifier = Modifier.size(4.dp))
                Text(text = it.name)
            }

        }

        loadingProgressText?.let {
            item {
                Text(text = stringResource(id = it))
            }
        }

        if(showLoadingButton) {
            item {
                Button(onClick = loadButtonClick) {
                    Text(text = stringResource(id = loadButtonText))
                }
            }
        }
    }
}