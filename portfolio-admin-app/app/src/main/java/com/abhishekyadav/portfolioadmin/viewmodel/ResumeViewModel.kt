package com.abhishekyadav.portfolioadmin.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhishekyadav.portfolioadmin.data.api.RetrofitClient
import com.abhishekyadav.portfolioadmin.data.model.Resume
import com.abhishekyadav.portfolioadmin.data.repository.ResumeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class ResumeViewModel(context: Context) : ViewModel() {


    private val api = RetrofitClient.getApiService(context)
    private val repository = ResumeRepository(api)

    private val _resumes = MutableStateFlow<Resume?>(null)
    val resumes: StateFlow<Resume?> = _resumes


    fun loadResumes() {
        viewModelScope.launch {
            try {
                _resumes.value = repository.getResume()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun uploadResume(
        file: MultipartBody.Part,
        resumeTitle: RequestBody
    ) {
        viewModelScope.launch {
            try {
                repository.uploadResume(file, resumeTitle)
                loadResumes()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }



    fun deleteResume(id: Long) {
        viewModelScope.launch {
            try {
                repository.deleteResume(id)
                _resumes.value = null
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
