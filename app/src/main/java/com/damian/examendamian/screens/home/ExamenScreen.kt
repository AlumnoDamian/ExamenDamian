@file:OptIn(ExperimentalMaterial3Api::class)

package com.damian.examendamian.screens.home

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.damian.examendamian.R
import com.damian.examendamian.screens.others.ImageCardScreen
import com.damian.examendamian.screens.others.LoginScreen

enum class ExamenApp {
    Login,
    ImageCard
}

@Composable
fun ExamenAppBar(currentScreen: ExamenApp) {
    TopAppBar(
        title = { Text("Material3") },
        actions = {
            Icon(
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = "Search",
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.baseline_more_vert_24),
                contentDescription = "Options",
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}

@Composable
fun ExamenScreen(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = ExamenApp.valueOf(backStackEntry?.destination?.route ?: ExamenApp.Login.name)

    Scaffold(
        topBar = {
            ExamenAppBar(
                currentScreen = currentScreen
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = ExamenApp.Login.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            composable(route = ExamenApp.Login.name) {
                LoginScreen(modifier = Modifier.fillMaxSize()){
                    navController.navigate(ExamenApp.ImageCard.name)
                }
            }
            composable(route = ExamenApp.ImageCard.name) {
                ImageCardScreen()
            }
        }
    }
}