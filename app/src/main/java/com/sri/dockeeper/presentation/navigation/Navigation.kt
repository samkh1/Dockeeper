package com.sri.dockeeper.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sri.dockeeper.presentation.MenuScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.MENU.route) {
        composable(route = Screens.MENU.route) {
            MenuScreen( ) {
                
            }
        }
    }
}
