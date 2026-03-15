package com.abhishekyadav.portfolioadmin.ui.screens.verifyotp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.abhishekyadav.portfolioadmin.ui.navigation.Routes
import com.abhishekyadav.portfolioadmin.viewmodel.AuthViewModel

@Composable
fun VerifyOtpScreen(navController: NavController, email: String) {
    val context = navController.context
    val viewModel = remember { AuthViewModel(context) }
    var otp by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text("Verify OTP", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = otp,
            onValueChange = { otp = it },
            label = { Text("OTP") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                if (otp.isBlank()) {
                    errorMessage = "OTP required"
                    return@Button
                }

                viewModel.verifyOtp(email, otp) { success ->
                    if (success) {
                        navController.navigate(Routes.HOME){
                            popUpTo(Routes.LOGIN) { inclusive = true }
                        }
                    } else {
                        errorMessage = "Invalid OTP"
                    }
                }

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Verify")
        }

        errorMessage?.let {
            Spacer(modifier = Modifier.height(10.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }
    }
}
