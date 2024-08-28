package com.sri.dockeeper.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sri.dockeeper.R
import com.sri.dockeeper.ui.theme.DocKeeperTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuScreen(
    onSaveAction: () -> Unit,
    onNavigateBack: () -> Unit,
) {
    Scaffold(topBar = {
        TopBar(onAction = { onNavigateBack() }, showNavigationIcon = false)
    }, modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            MenuButton(onButtonClick = { onSaveAction() }, label = stringResource(id = R.string.save_label))
            Spacer(modifier = Modifier.padding(all = 20.dp))
            MenuButton(
                onButtonClick = {
                    // ListDocScreen(listDocs = listDoc)
                },
                label = stringResource(id = R.string.list_label),
            )
        }
    }
}

@Composable
fun MenuButton(
    onButtonClick: () -> Unit,
    label: String,
) {
    Button(
        onClick = { onButtonClick() },
        modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp),
        shape = RoundedCornerShape(10),

    ) {
        Text(text = label)
    }
}

@Preview
@Composable
fun showMenuButton() {
    MenuButton(label = "test", onButtonClick = { })
}

@Composable
@Preview
fun showMenuScreen() {
    DocKeeperTheme {
        /*MenuScreen(
            onSaveAction = {},
            showList = {},
            onNavigateBack = {},
        )*/
    }
}
