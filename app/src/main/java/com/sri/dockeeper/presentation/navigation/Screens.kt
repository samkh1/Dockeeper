package com.sri.dockeeper.presentation.navigation

sealed class Screens(val route: String) {
    object MENU : Screens("Menu_Screen")
    object List : Screens("List_Screen")
}
