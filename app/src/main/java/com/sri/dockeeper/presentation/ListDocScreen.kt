package com.sri.dockeeper.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sri.dockeeper.domain.model.Document
import com.sri.dockeeper.ui.theme.DocKeeperTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListDocScreen(listDocs: List<Document>) {
    Scaffold(
        topBar = { TopBar(onAction = { /*TODO*/ }, showNavigationIcon = true) },
        bottomBar = { BottomBar() },
    ) { innerPading ->
        var isItemSelected by remember { mutableStateOf(false) }
        var selectedOption by remember { mutableStateOf(listDocs[0]) }

        LazyColumn(modifier = Modifier.fillMaxSize().padding(innerPading)) {
            items(listDocs) { doc ->
                DocItem(
                    doc,
                    {
                        selectedOption = doc
                        isItemSelected = true
                    },
                    isItemSelected = isItemSelected,
                )
            }
        }
    }
}

@Composable
fun DocItem(doc: Document, onRadioButtonClick: () -> Unit, isItemSelected: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                enabled = true,
                selected = isItemSelected,
                onClick = { onRadioButtonClick() },
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = "${doc.title} - ${doc.date}", color = MaterialTheme.colorScheme.primary)
        RadioButton(
            selected = isItemSelected,
            onClick = { onRadioButtonClick() },
        )
    }
}

@Preview
@Composable
fun showDocItem() {
    DocKeeperTheme {
        //  DocItem(listDocs = listOf())
    }
}
