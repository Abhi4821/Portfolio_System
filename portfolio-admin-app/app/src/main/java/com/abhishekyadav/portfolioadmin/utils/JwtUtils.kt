package com.abhishekyadav.portfolioadmin.utils

import android.util.Base64
import org.json.JSONObject
object JwtUtils {
    fun isTokenExpired(token: String): Boolean {
        return try {
            val parts = token.split(".")
            val payload = String(
                Base64.decode(parts[1], Base64.URL_SAFE)
            )
            val json = JSONObject(payload)
            val exp = json.getLong("exp")
            val now = System.currentTimeMillis() / 1000
            now >= exp
        } catch (e: Exception) {
            true
        }
    }
}