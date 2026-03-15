package com.abhishekyadav.portfolioadmin.data.repository
import com.abhishekyadav.portfolioadmin.data.api.ApiService
import retrofit2.Response
class AuthRepository(private val api: ApiService) {
    suspend fun sendOtp(email: String): Response<String> {
        return api.sendOtp(email)
    }
    suspend fun verifyOtp(email: String, otp: String): Response<String> {
        return api.verifyOtp(email, otp)
    }
}
