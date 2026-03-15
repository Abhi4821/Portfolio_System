package com.abhishekyadav.portfolioadmin.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhishekyadav.portfolioadmin.data.api.RetrofitClient
import com.abhishekyadav.portfolioadmin.data.model.Project
import com.abhishekyadav.portfolioadmin.data.repository.ProjectRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class ProjectViewModel(context: Context) : ViewModel() {


    private val api = RetrofitClient.getApiService(context)
    private val repository = ProjectRepository(api)

    private val _projects = MutableStateFlow<List<Project>>(emptyList())
    val projects: StateFlow<List<Project>> = _projects

    fun loadProjects() {
        viewModelScope.launch {
            try {
                _projects.value = repository.getProjects()
            } catch (e: Exception) {
            }
        }
    }

    fun addProject(
        title: RequestBody,
        description: RequestBody,
        sourceCode: RequestBody,
        demoUrl: RequestBody,
        image: MultipartBody.Part?
    ) {
        viewModelScope.launch {
            try {
                repository.addProject(
                    title,
                    description,
                    sourceCode,
                    demoUrl,
                    image
                )
                loadProjects()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    fun deleteProject(id: Long) {
        viewModelScope.launch {
            repository.deleteProject(id)
            loadProjects()
        }
    }
}
