package com.sri.dockeeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.sri.dockeeper.presentation.MenuScreen
import com.sri.dockeeper.ui.theme.DocKeeperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DocKeeperTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    MenuScreen(onSaveAction = { /*TODO*/ }, onNavigateBack = {})
                }
            }
        }
    }
    companion object {
        const val ENSEIGNE = "Maison Al Amir"
    }
}
