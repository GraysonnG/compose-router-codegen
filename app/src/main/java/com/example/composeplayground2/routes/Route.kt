package com.example.composeplayground2.routes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import com.blanktheevil.router.Routes

object Router {
    @Composable
    fun NavGraph(
        modifier: Modifier,
        navController: NavHostController,
        startDestination: String,
    ) = NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        Routes(navController).forEach { entry ->
            composable(entry.key) {
                entry.value()
            }
        }

    }
}