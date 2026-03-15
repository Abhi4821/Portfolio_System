package com.abhishekyadav.portfolioadmin.data.model
data class OtpRequest(
    val email: String,
    val otp: String? = null
)