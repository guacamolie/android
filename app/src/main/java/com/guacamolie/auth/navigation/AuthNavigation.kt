package com.guacamolie.auth.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.guacamolie.auth.ui.screen.LoginScreen
import com.guacamolie.auth.ui.screen.RegisterScreen
import com.guacamolie.auth.ui.screen.SuccessScreen

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Success : Screen("success")
}

@Composable
fun AuthNavigation(
    navController: NavHostController = rememberNavController()
) {
    var jwtToken by remember { mutableStateOf<String?>(null) }
    
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route)
                },
                onLoginSuccess = { token ->
                    jwtToken = token
                    navController.navigate(Screen.Success.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        
        composable(Screen.Register.route) {
            RegisterScreen(
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Register.route) { inclusive = true }
                    }
                },
                onRegisterSuccess = { token ->
                    jwtToken = token
                    navController.navigate(Screen.Success.route) {
                        popUpTo(Screen.Register.route) { inclusive = true }
                    }
                }
            )
        }
        
        composable(Screen.Success.route) {
            SuccessScreen(
                token = jwtToken ?: "",
                onLogout = {
                    jwtToken = null
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Success.route) { inclusive = true }
                    }
                }
            )
        }
    }
}