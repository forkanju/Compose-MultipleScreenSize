package com.example.compose_multiplescreensize

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.compose_multiplescreensize.navigation.SetupNavGraph
import com.example.compose_multiplescreensize.ui.theme.ComposeMultipleScreenSizeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMultipleScreenSizeTheme {
                val window = rememberWindowSize()
                val navController = rememberNavController()
                SetupNavGraph(windowSize = window, navController = navController)
            }
        }
    }
}

