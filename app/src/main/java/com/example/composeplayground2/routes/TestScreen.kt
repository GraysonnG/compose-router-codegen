package com.example.composeplayground2.routes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.blanktheevil.router.ComposableRoute

//object TestScreen: Route() {
//    override val id: String
//        get() = "test"
//
//    @OptIn(ExperimentalMaterial3Api::class)
//    @Composable
//    override fun Screen(navController: NavController) {
//
//    }
//}

const val TEST_ROUTE = "test"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@ComposableRoute(TEST_ROUTE)
fun TestScreenRoute(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Test Screen")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {

        }
    }
}