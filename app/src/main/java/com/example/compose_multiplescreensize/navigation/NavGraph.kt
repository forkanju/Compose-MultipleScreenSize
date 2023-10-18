package com.example.compose_multiplescreensize.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.compose_multiplescreensize.WindowSize
import com.example.compose_multiplescreensize.screen.home.HomeScreen

@Composable
fun SetupNavGraph(windowSize: WindowSize, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route){
            HomeScreen(windowSize = windowSize)
        }
    }

}