package com.sri.dockeeper.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sri.dockeeper.presentation.ListDocScreen
import com.sri.dockeeper.presentation.MenuScreen
import com.sri.dockeeper.presentation.ScanDocScreen
import com.sri.dockeeper.presentation.ViewModel

@Composable
fun Navigation(navController: NavHostController) {
    val viewModel: ViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screens.MENU.route) {
        composable(route = Screens.MENU.route) {
            MenuScreen(navController)
        }

        composable(route = Screens.Scan.route) {
            ScanDocScreen(navController = navController, viewModel)
        }
        
        composable(route = Screens.List.route) {
            ListDocScreen(viewModel, navController)
        }
    }
}
