package com.abhishekyadav.portfolioadmin.data.api

import android.content.Context
import com.abhishekyadav.portfolioadmin.utils.JwtInterceptor
import com.abhishekyadav.portfolioadmin.utils.TokenManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
//    private const val BASE_URL = "http://10.0.2.2:8081/"
//    private const val BASE_URL = "http://192.168.29.234:8081/"
//    private const val BASE_URL = "http://13.126.38.72:8080"
    private const val BASE_URL = "https://abhishek-portfolio.duckdns.org";


    private var apiService: ApiService? = null
    fun getApiService(context: Context): ApiService {
        if (apiService == null) {
            val tokenManager = TokenManager(context)
            val client = OkHttpClient.Builder()
                .addInterceptor(JwtInterceptor(tokenManager))
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(retrofit2.converter.scalars.ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            apiService = retrofit.create(ApiService::class.java)
        }
        return apiService!!
    }
}
