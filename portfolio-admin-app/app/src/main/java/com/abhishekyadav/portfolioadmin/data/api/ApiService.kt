package com.abhishekyadav.portfolioadmin.data.api

import com.abhishekyadav.portfolioadmin.data.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody

import retrofit2.Response
import retrofit2.http.POST

import retrofit2.http.*




interface ApiService {

    // ---------------- AUTH ----------------


    @POST("api/adi/auth/send-otp")
    suspend fun sendOtp(
        @Query("email") email: String
    ): Response<String>

    @POST("api/adi/auth/verify-otp")
    suspend fun verifyOtp(
        @Query("email") email: String,
        @Query("otp") otp: String
    ): Response<String>


    // ---------------- SKILLS ----------------

    @GET("api/usr/skills")
    suspend fun getSkills(): List<Skill>


    @Multipart
    @POST("api/adi/skills")
    suspend fun addSkill(
        @Part("skillName") skillName: RequestBody,
        @Part("skillLevel") skillLevel: RequestBody?,
        @Part certificate: MultipartBody.Part?
    ): Response<Skill>

    @DELETE("api/adi/skills/{id}")
    suspend fun deleteSkill(
        @Path("id") id: Long
    ): Response<Void>


    // ---------------- PROJECTS ----------------

    @GET("api/usr/projects")
    suspend fun getProjects(): List<Project>

    @Multipart
    @POST("api/adi/projects")
    suspend fun addProject(
        @Part("projectTitle") title: RequestBody,
        @Part("projectDescription") description: RequestBody,
        @Part("sourceCodeUrl") sourceCode: RequestBody,
        @Part("demoUrl") demoUrl: RequestBody,
        @Part image: MultipartBody.Part?
    ): Response<Project>

    @DELETE("api/adi/projects/{id}")
    suspend fun deleteProject(
        @Path("id") id: Long
    ): Response<Void>


    // ---------------- RESUME ----------------

    @GET("api/usr/resume")
    suspend fun getResume(): Resume


    @Multipart
    @POST("api/adi/resume")
    suspend fun uploadResume(
        @Part file: MultipartBody.Part,
        @Part("resumeTitle") resumeTitle: RequestBody
    ): Response<Resume>

    @DELETE("api/adi/resume/{id}")
    suspend fun deleteResume(
        @Path("id") id: Long
    ): Response<Void>


    // ---------------- MESSAGES ----------------

    @GET("api/adi/messages")
    suspend fun getMessages(): List<Message>

    @DELETE("api/adi/messages/{id}")
    suspend fun deleteMessage(
        @Path("id") id: Long
    ): Response<Void>

}