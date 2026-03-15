package com.abhishekyadav.portfolioadmin.utils

import okhttp3.Interceptor
import okhttp3.Response
class JwtInterceptor(private val tokenManager: TokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val token = tokenManager.getToken()
        val requestBuilder = originalRequest.newBuilder()
        if (token != null) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }
        val response = chain.proceed(requestBuilder.build())
        if (response.code == 401) {
            tokenManager.clearToken()
        }
        return response
    }
}
