package com.abhishekyadav.portfolioadmin.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhishekyadav.portfolioadmin.data.api.RetrofitClient
import com.abhishekyadav.portfolioadmin.data.repository.AuthRepository
import com.abhishekyadav.portfolioadmin.utils.TokenManager
import kotlinx.coroutines.launch

class AuthViewModel(context: Context) : ViewModel() {
    private val api = RetrofitClient.getApiService(context)
    private val repository = AuthRepository(api)
    private val tokenManager = TokenManager(context)

    fun sendOtp(email: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.sendOtp(email)
                val body = response.body()
                println("STATUS CODE = ${response.code()}")
                println("BODY = $body")
                val success = response.code() == 200 && body == "OTP sent successfully"
                kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.Main) {
                    onResult(success)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.Main) {
                    onResult(false)
                }
            }
        }
    }

    fun verifyOtp(email: String, otp: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.verifyOtp(email, otp)
                if (response.isSuccessful) {
                    val token = response.body()
                    if (!token.isNullOrEmpty()) {
                        tokenManager.saveToken(token)
                        onResult(true)
                    } else {
                        onResult(false)
                    }
                } else {
                    onResult(false)
                }
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }
}
