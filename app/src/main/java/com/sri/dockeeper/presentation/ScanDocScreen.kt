package com.sri.dockeeper.presentation

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sri.dockeeper.ui.theme.DocKeeperTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanDocScreen() {
    Scaffold(
        topBar = {
            TopBar(
                onAction = { /*TODO*/ },
                showNavigationIcon = true,
            )
        },
        bottomBar = {
            BottomBar()
        },
    ) {

    }
}

@Preview
@Composable
fun showScanDocScreen() {
    DocKeeperTheme {
        ScanDocScreen()
    }
}
