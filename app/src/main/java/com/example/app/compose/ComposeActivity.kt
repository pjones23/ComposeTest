package com.example.app.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.app.compose.composables.FeedContainer
import com.example.app.compose.composables.RickAndMortyAppBar
import com.example.app.compose.theme.AppTheme

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Scaffold(
                    topBar = {
                        RickAndMortyAppBar()
                    },
                    content = { padding ->
                        Surface {
                            FeedContainer(modifier = Modifier.padding(padding))
                        }
                    }
                )

            }
        }
    }
}