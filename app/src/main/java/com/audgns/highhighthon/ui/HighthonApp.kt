package com.audgns.highhighthon.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.audgns.highhighthon.ui.login.LoginScreen
import com.audgns.highhighthon.ui.main.MainScreen
import com.audgns.highhighthon.ui.qr.QrLoginScreen

@Composable
fun HighthonApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "app"
    ) {
        navigation(
            route = "app",
            startDestination = "login"
        ) {
            composable("login") {
                LoginScreen(navController = navController)
            }

            composable("qrScreen") {
                QrLoginScreen(navController = navController)
            }

            composable("main") {
                MainScreen()
            }
        }
    }
}