package com.abhishekyadav.portfolioadmin.data.repository

import com.abhishekyadav.portfolioadmin.data.api.ApiService
import com.abhishekyadav.portfolioadmin.data.model.Skill
import okhttp3.MultipartBody
import okhttp3.RequestBody

class SkillRepository(private val api: ApiService) {
    suspend fun getSkills(): List<Skill> {
        return api.getSkills()
    }
    suspend fun addSkill(
        name: RequestBody,
        level: RequestBody,
        certificate: MultipartBody.Part?
    ) = api.addSkill(name, level, certificate)

    suspend fun deleteSkill(id: Long) {
        api.deleteSkill(id)
    }
}
