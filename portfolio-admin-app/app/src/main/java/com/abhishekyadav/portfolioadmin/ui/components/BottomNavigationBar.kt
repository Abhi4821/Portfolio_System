//package com.abhishekyadav.portfolioadmin.ui.components
//import androidx.navigation.compose.rememberNavController
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Build
//import androidx.compose.material.icons.filled.Close
//import androidx.compose.material.icons.filled.Description
//import androidx.compose.material.icons.filled.Email
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.filled.LaptopChromebook
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.navigation.NavController
//import androidx.navigation.NavHostController
//import com.abhishekyadav.portfolioadmin.ui.navigation.Routes
//
//@Composable
//fun BottomNavigationBar(navController: NavController) {
//
//
//    NavigationBar {
//        NavigationBarItem(
//            selected = false,
//            onClick = { navController.navigate(Routes.SKILLS) },
//            label = { Text("Skills") },
//            icon = { Icon(Icons.Default.Build, contentDescription = "Skills") }
//        )
//
//        NavigationBarItem(
//            selected = false,
//            onClick = { navController.navigate(Routes.PROJECTS) },
//            label = { Text("Projects") },
//            icon = { Icon(Icons.Default.LaptopChromebook, contentDescription = "Projects") }
//        )
//
//        NavigationBarItem(
//            selected = false,
//            onClick = { navController.navigate(Routes.RESUME) },
//            label = { Text("Resume") },
//            icon = { Icon(Icons.Default.Description, contentDescription = "Resume") }
//        )
//
//        NavigationBarItem(
//            selected = false,
//            onClick = { navController.navigate(Routes.MESSAGES) },
//            label = { Text("Messages") },
//            icon = { Icon(Icons.Default.Email, contentDescription = "Messages") }
//        )
//
//    }
//}




package com.abhishekyadav.portfolioadmin.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LaptopChromebook
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.abhishekyadav.portfolioadmin.ui.navigation.Routes

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route

    NavigationBar {

        NavigationBarItem(
            selected = currentRoute == Routes.SKILLS,
            onClick = {
                navController.navigate(Routes.SKILLS) {
                    launchSingleTop = true
                    restoreState = true
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                }
            },
            icon = {
                Icon(Icons.Default.Build, contentDescription = "Skills")
            },
            label = {
                Text("Skills")
            }
        )

        NavigationBarItem(
            selected = currentRoute == Routes.PROJECTS,
            onClick = {
                navController.navigate(Routes.PROJECTS) {
                    launchSingleTop = true
                    restoreState = true
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                }
            },
            icon = {
                Icon(Icons.Default.LaptopChromebook, contentDescription = "Projects")
            },
            label = {
                Text("Projects")
            }
        )

        NavigationBarItem(
            selected = currentRoute == Routes.RESUME,
            onClick = {
                navController.navigate(Routes.RESUME) {
                    launchSingleTop = true
                    restoreState = true
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                }
            },
            icon = {
                Icon(Icons.Default.Description, contentDescription = "Resume")
            },
            label = {
                Text("Resume")
            }
        )

        NavigationBarItem(
            selected = currentRoute == Routes.MESSAGES,
            onClick = {
                navController.navigate(Routes.MESSAGES) {
                    launchSingleTop = true
                    restoreState = true
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                }
            },
            icon = {
                Icon(Icons.Default.Email, contentDescription = "Messages")
            },
            label = {
                Text("Messages")
            }
        )
    }
}