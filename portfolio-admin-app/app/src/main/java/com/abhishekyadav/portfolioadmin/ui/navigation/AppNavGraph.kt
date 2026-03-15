package com.abhishekyadav.portfolioadmin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.abhishekyadav.portfolioadmin.ui.screens.HomeScreen
import com.abhishekyadav.portfolioadmin.ui.screens.login.LoginScreen
import com.abhishekyadav.portfolioadmin.ui.screens.splash.SplashScreen
import com.abhishekyadav.portfolioadmin.ui.screens.verifyotp.VerifyOtpScreen
import com.abhishekyadav.portfolioadmin.ui.screens.skills.SkillsScreen
import com.abhishekyadav.portfolioadmin.ui.screens.projects.ProjectsScreen
import com.abhishekyadav.portfolioadmin.ui.screens.resume.ResumeScreen
import com.abhishekyadav.portfolioadmin.ui.screens.messages.MessagesScreen

@Composable
fun AppNavGraph(navController: NavHostController) {


    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {


        composable(Routes.SPLASH) {
            SplashScreen(navController)
        }

        composable(Routes.LOGIN) {
            LoginScreen(navController)
        }

        composable(Routes.HOME) {
            HomeScreen(navController)
        }


        composable("${Routes.VERIFY_OTP}/{email}") { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            VerifyOtpScreen(
                navController = navController,
                email = email
            )
        }

        composable(Routes.SKILLS) {
            SkillsScreen(navController)
        }

        composable(Routes.PROJECTS) {
            ProjectsScreen(navController)
        }

        composable(Routes.RESUME) {
            ResumeScreen(navController)
        }

        composable(Routes.MESSAGES) {
            MessagesScreen(navController)
        }

    }


}
