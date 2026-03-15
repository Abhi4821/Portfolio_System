package com.abhishekyadav.portfolioadmin.data.repository

import com.abhishekyadav.portfolioadmin.data.api.ApiService
import com.abhishekyadav.portfolioadmin.data.model.Project
import okhttp3.MultipartBody
import okhttp3.RequestBody

class ProjectRepository(private val api: ApiService) {
    suspend fun getProjects(): List<Project> {
        return api.getProjects()
    }
    suspend fun addProject(
        title: RequestBody,
        description: RequestBody,
        sourceCode: RequestBody,
        demoUrl: RequestBody,
        image: MultipartBody.Part?
    ) = api.addProject(title, description, sourceCode, demoUrl, image)
    suspend fun deleteProject(id: Long) {
        api.deleteProject(id)
    }
}
