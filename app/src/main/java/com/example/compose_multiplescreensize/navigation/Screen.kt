package com.example.compose_multiplescreensize.navigation

sealed class Screen(val route: String){
    object Home : Screen("home")
}
