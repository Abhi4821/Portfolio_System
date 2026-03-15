package com.abhishekyadav.portfolioadmin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.abhishekyadav.portfolioadmin.ui.navigation.AppNavGraph
import com.abhishekyadav.portfolioadmin.ui.theme.PortfolioAdminTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PortfolioAdminTheme {
                val navController = rememberNavController()
                AppNavGraph(navController)
            }
        }
    }
}
