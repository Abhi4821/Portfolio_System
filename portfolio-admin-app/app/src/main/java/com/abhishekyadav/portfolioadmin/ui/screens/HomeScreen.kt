//package com.abhishekyadav.portfolioadmin.ui.screens
//
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.navigation.NavController
//import com.abhishekyadav.portfolioadmin.ui.components.BottomNavigationBar
//
//@Composable
//fun HomeScreen(navController: NavController) {
//
//    Scaffold(
//        bottomBar = {
//            BottomNavigationBar(navController)
//        }
//    ) { paddingValues ->
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues)
//        ) {
//        }
//    }
//}
//


package com.abhishekyadav.portfolioadmin.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abhishekyadav.portfolioadmin.ui.components.BottomNavigationBar
import com.abhishekyadav.portfolioadmin.ui.navigation.Routes
import com.abhishekyadav.portfolioadmin.ui.screens.messages.MessagesScreen
import com.abhishekyadav.portfolioadmin.ui.screens.projects.ProjectsScreen
import com.abhishekyadav.portfolioadmin.ui.screens.resume.ResumeScreen
import com.abhishekyadav.portfolioadmin.ui.screens.skills.SkillsScreen

@Composable
fun HomeScreen() {

    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(bottomNavController)
        }
    ) { paddingValues ->

        NavHost(
            navController = bottomNavController,
            startDestination = Routes.SKILLS,
            modifier = Modifier.padding(paddingValues)
        ) {

            composable(Routes.SKILLS) {
                SkillsScreen(bottomNavController)
            }

            composable(Routes.PROJECTS) {
                ProjectsScreen(bottomNavController)
            }

            composable(Routes.RESUME) {
                ResumeScreen(bottomNavController)
            }

            composable(Routes.MESSAGES) {
                MessagesScreen(bottomNavController)
            }
        }
    }
}