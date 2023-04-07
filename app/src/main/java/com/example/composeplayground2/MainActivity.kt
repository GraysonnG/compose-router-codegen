package com.example.composeplayground2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.composeplayground2.routes.HOME
import com.example.composeplayground2.routes.Router
import com.example.composeplayground2.ui.theme.ComposePlayground2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlayground2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Router.NavGraph(
                        modifier = Modifier,
                        navController = rememberNavController(),
                        startDestination = HOME
                    )
                }
            }
        }
    }
}