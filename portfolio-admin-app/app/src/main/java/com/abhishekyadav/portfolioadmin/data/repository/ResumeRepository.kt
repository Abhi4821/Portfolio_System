package com.abhishekyadav.portfolioadmin.data.repository

import com.abhishekyadav.portfolioadmin.data.api.ApiService
import com.abhishekyadav.portfolioadmin.data.model.Resume
import okhttp3.MultipartBody
import okhttp3.RequestBody
class ResumeRepository(private val api: ApiService) {
    suspend fun getResume(): Resume {
        return api.getResume()
    }
    suspend fun uploadResume(
        file: MultipartBody.Part,
        resumeJson: RequestBody
    ) {
        api.uploadResume(file, resumeJson)
    }
    suspend fun deleteResume(id: Long) {
        api.deleteResume(id)
    }
}
