package com.sri.dockeeper.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.sri.dockeeper.R

@Composable
fun EmptyList() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = stringResource(id = R.string.empty_list), textAlign = TextAlign.Center)
    }
}

@Preview
@Composable
fun showEmptyList() {
    EmptyList()
}
