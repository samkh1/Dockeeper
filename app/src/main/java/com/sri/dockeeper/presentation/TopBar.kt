package com.sri.dockeeper.presentation

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.sri.dockeeper.MainActivity.Companion.ENSEIGNE
import com.sri.dockeeper.ui.theme.DocKeeperTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onAction: () -> Unit, showNavigationIcon: Boolean) {
    TopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        title = { Text(text = ENSEIGNE, color = Color.White) },
        modifier = Modifier.background(MaterialTheme.colorScheme.primary),
        navigationIcon = {
            if (showNavigationIcon) {
                IconButton(
                    onClick = { onAction() },
                ) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Back to last screen",
                        tint = Color.White,
                    )
                }
            }
        },
    )
}

@Preview
@Composable
fun showTopBar() {
    DocKeeperTheme {
        TopBar(onAction = { /*TODO*/ }, showNavigationIcon = true)
    }
}
