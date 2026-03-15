package com.abhishekyadav.portfolioadmin.ui.screens.splash

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.abhishekyadav.portfolioadmin.data.api.RetrofitClient
import com.abhishekyadav.portfolioadmin.ui.navigation.Routes
import com.abhishekyadav.portfolioadmin.utils.JwtUtils
import com.abhishekyadav.portfolioadmin.utils.TokenManager
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {

    val context = navController.context
    val tokenManager = TokenManager(context)

    val api = RetrofitClient.getApiService(context)

    LaunchedEffect(Unit) {

        delay(3000)

        val token = tokenManager.getToken()

        if (token == null) {
            navController.navigate(Routes.LOGIN) {
                popUpTo(Routes.SPLASH) { inclusive = true }
            }
        } else {
            try {
                //  protected API call
                api.deleteSkill(1)
                // अगर API success है → token valid
                navController.navigate(Routes.HOME) {
                    popUpTo(Routes.SPLASH) { inclusive = true }
                }

            } catch (e: Exception) {
                //  अगर 401 आया
                tokenManager.clearToken()
                navController.navigate(Routes.LOGIN) {
                    popUpTo(Routes.SPLASH) { inclusive = true }
                }

            }

        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                "Portfolio Admin",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            CircularProgressIndicator()

        }

    }
}

