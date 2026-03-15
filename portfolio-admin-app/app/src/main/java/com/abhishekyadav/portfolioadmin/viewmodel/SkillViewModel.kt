package com.abhishekyadav.portfolioadmin.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhishekyadav.portfolioadmin.data.api.RetrofitClient
import com.abhishekyadav.portfolioadmin.data.model.Skill
import com.abhishekyadav.portfolioadmin.data.repository.SkillRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class SkillViewModel(context: Context) : ViewModel() {
    private val api = RetrofitClient.getApiService(context)
    private val repository = SkillRepository(api)
    private val _skills = MutableStateFlow<List<Skill>>(emptyList())
    val skills: StateFlow<List<Skill>> = _skills

    fun loadSkills() {
        viewModelScope.launch {
            try {
                _skills.value = repository.getSkills()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addSkill(
        name: RequestBody,
        level: RequestBody,
        certificate: MultipartBody.Part?
    ) {
        viewModelScope.launch {
            try {
                repository.addSkill(name, level, certificate)
                loadSkills()
            } catch (e: Exception) {
            }
        }
    }

    fun deleteSkill(id: Long) {
        viewModelScope.launch {
            try {
                repository.deleteSkill(id)
                loadSkills()
            } catch (e: Exception) {
            }
        }
    }

}
