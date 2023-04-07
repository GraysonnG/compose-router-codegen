package com.example.composeplayground2.routes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.blanktheevil.router.ComposableRoute

const val HOME = "home"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@ComposableRoute(HOME)
fun HomeScreenRoute(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("Home Screen")
            })
        }
    ) {
        Column(modifier = Modifier
            .padding(it)
            .padding(8.dp)
            .fillMaxSize()) {
            Button(
                onClick = {
                    navController.navigate(TEST_ROUTE)
                }
            ) {
                Text("Test Screen")
            }
        }
    }
}