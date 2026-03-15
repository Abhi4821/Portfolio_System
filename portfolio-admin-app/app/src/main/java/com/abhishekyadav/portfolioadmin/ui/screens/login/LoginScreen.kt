package com.abhishekyadav.portfolioadmin.ui.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.abhishekyadav.portfolioadmin.ui.navigation.Routes
import com.abhishekyadav.portfolioadmin.viewmodel.AuthViewModel

@Composable
fun LoginScreen(navController: NavController) {

    val context = navController.context
    val viewModel = remember { AuthViewModel(context) }

    var email by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Admin Login", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {

                if (email.isBlank()) {
                    errorMessage = "Email required"
                    return@Button
                }

                viewModel.sendOtp(email) { success ->

                    println("OTP SUCCESS = $success")

                    if (success) {

                        navController.navigate("${Routes.VERIFY_OTP}/$email")

                    } else {

                        errorMessage = "Failed to send OTP"

                    }
                }

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Send OTP")
        }
        errorMessage?.let {
            Spacer(modifier = Modifier.height(10.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }
    }
}
